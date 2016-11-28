<?php

// Redirection pour un utilisateur non login
if (!isLogin()) {
    header('Location: index.php');
}

if (!isset($playlistId)) {
    header('Location: index.php');
}

require_once dirname(__FILE__).'/../dao/playlist.php';

// Suppression d'une liste de track sélectionné
$trackIds = filter_input(INPUT_POST, 'remTrackFromPlaylist', FILTER_SANITIZE_NUMBER_INT, FILTER_REQUIRE_ARRAY);

if (!empty($trackIds)) {
    playlistDAO::deleteTracks($playlistId, $trackIds);
}

// Suppression d'une track unique
$trackId = filter_input(INPUT_GET, 'id', FILTER_SANITIZE_NUMBER_INT);

if (!empty($trackId)) {
    playlistDAO::deleteTracks($playlistId, $trackId);
}
header('Location: index.php?action=thisPlaylist');