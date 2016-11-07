<?php

// Chargement des fonctions divers
require('common.php');

// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
    die();
}

$id = filter_input(INPUT_GET, 'id', FILTER_SANITIZE_NUMBER_INT);

if (!empty($id)) {
    if (isset($playlistId)) {
        if ($playlistId == $id) {
            unset($_SESSION['last_Playlist']);
        }
    }

    $insert = bdd()->prepare('DELETE FROM `playlist-header` WHERE id = :playlistid');
    $insert->bindValue(":playlistid", (int) $id);
    $insert->execute();
}
refresh('showUserPlaylists.php');
