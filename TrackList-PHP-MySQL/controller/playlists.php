<?php

require_once dirname(__FILE__) . '/../dao/playlist.php';

$playlists = playlistDAO::getAll();

include dirname(__FILE__) . '/../view/playlistsView.php';