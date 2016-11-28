<?php

// Redirection pour un utilisateur non login
if (!isLogin()) {
    header('Location: index.php');
}

require_once dirname(__FILE__).'/../dao/playlist.php';

// Récupération des divers variables GET/POST
$id = filter_input(INPUT_POST, 'id', FILTER_SANITIZE_NUMBER_INT);

$playlist = playlistDAO::getById($id);

if (empty($data) || $data == -1) {
    header('Location: index.php');
}
$playlist->setUserName($username);
$playlist->setUserId($userid);

$newPlaylistId = playlistDAO::insert($playlist);

$tracks = trackDAO::getFromPlaylist($id);
foreach ($tracks as $track) {
    playlistDAO::insertTrack($newPlaylistId, $track->getId());    
}
header('Location: index.php?action=thisPlaylist&playlistId=' . $newPlaylistId);