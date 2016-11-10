<?php

require_once dirname(__FILE__) . '/../dao/playlist.php';
$playlists = playlistDAO::getAllByUserId($userid);
include 'view/myPlaylistsView.php';

