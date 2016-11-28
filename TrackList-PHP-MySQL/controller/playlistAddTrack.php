<?php

// Redirection pour un utilisateur non login
if (!isLogin()) {
    header('Location: index.php');
}

if (!isset($playlistId)) {
    header('Location: index.php');
}

require_once dirname(__FILE__).'/../dao/playlist.php';

$playlistAddTracks = filter_input(INPUT_POST, 'playlistAddTracks', FILTER_SANITIZE_NUMBER_INT, FILTER_REQUIRE_ARRAY);

if (!empty($playlistAddTracks)) {
    foreach ($playlistAddTracks as $trackId) {
        playlistDAO::insertTrack($playlistId, $trackId);
    }
}
header('Location: index.php?action=thisPlaylist&playlistId=' . $playlistId);