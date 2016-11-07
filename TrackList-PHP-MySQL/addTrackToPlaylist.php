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

$addTrackToPlaylist = filter_input(INPUT_POST, 'addTrackToPlaylist', FILTER_SANITIZE_NUMBER_INT, FILTER_REQUIRE_ARRAY);

if (!empty($addTrackToPlaylist)) {
    foreach ($addTrackToPlaylist as $trackid) {
        $insert = bdd()->prepare('INSERT INTO `playlist` VALUES(:playlistid, :trackid)');
        $insert->bindValue(":playlistid", (int) $playlistId);
        $insert->bindValue(":trackid", (int) $trackid);
        $insert->execute();
    }
}
refresh('showPlaylistTracks.php');
