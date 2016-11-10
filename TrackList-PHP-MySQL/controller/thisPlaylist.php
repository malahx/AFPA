<?php
require_once dirname(__FILE__) . '/../dao/track.php';
$tracks = trackDAO::getFromPlaylist($playlistId);
include 'view/currentPlaylistView.php';
