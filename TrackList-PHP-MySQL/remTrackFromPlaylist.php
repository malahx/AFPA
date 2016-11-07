<?php

// Chargement des fonctions divers
require('common.php');

// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
    die();
}

if (!isset($playlistId)) {
    refresh();
}

// Suppression d'une liste de track sélectionné
$addTrackToPlaylist = filter_input(INPUT_POST, 'remTrackFromPlaylist', FILTER_SANITIZE_NUMBER_INT, FILTER_REQUIRE_ARRAY);

if (!empty($addTrackToPlaylist)) {
    foreach ($addTrackToPlaylist as $trackid) {
        $insert = bdd()->prepare('DELETE FROM playlist WHERE playlistid = :playlistid AND trackid = :trackid');
        $insert->bindValue(":playlistid", (int) $playlistId);
        $insert->bindValue(":trackid", (int) $trackid);
        $insert->execute();
    }
}

// Suppression d'une track unique
$trackid = filter_input(INPUT_GET, 'id', FILTER_SANITIZE_NUMBER_INT);

if (!empty($trackid)) {
    $insert = bdd()->prepare('DELETE FROM playlist WHERE playlistid = :playlistid AND trackid = :trackid');
    $insert->bindValue(":playlistid", (int) $playlistId);
    $insert->bindValue(":trackid", (int) $trackid);
    $insert->execute();
}
refresh('showPlaylistTracks.php');