<?php

// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
}

require_once dirname(__FILE__).'/../dao/playlist.php';

$id = filter_input(INPUT_GET, 'id', FILTER_SANITIZE_NUMBER_INT);

if (!empty($id)) {
    if (isset($playlistId)) {
        if ($playlistId == $id) {
            unset($_SESSION['last_Playlist']);
        }
    }

    playlistDAO::delete($id);
}
header('Location: index.php?action=myPlaylists');