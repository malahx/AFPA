<?php

// Redirection pour un utilisateur non login
if (!isLogin()) {
    header('Location: index.php');
}

require_once dirname(__FILE__).'/../dao/playlist.php';

if (empty($playlist) || !isset($playlistId)) {
    header('Location: index.php');
}
$playlist->setUserName($username);
$playlist->setUserId($userid);

$newPlaylistId = playlistDAO::insert($playlist);

$tracks = trackDAO::getFromPlaylist($playlistId);
foreach ($tracks as $track) {
    playlistDAO::insertTrack($newPlaylistId, $track->getId());    
}
header('Location: index.php?action=thisPlaylist&playlistId=' . $newPlaylistId);