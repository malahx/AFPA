<?php

// Chargement des fonctions divers
require_once 'common.inc.php';

$action = filter_input(INPUT_GET, 'action', FILTER_SANITIZE_SPECIAL_CHARS);
$login = filter_input(INPUT_POST, 'login');
$register = filter_input(INPUT_POST, 'register');
$playlistAdd = filter_input(INPUT_POST, 'playlistAdd');
$playlistAddTrack = filter_input(INPUT_POST, 'playlistAddTrack');
$playlistDeleteTrack = filter_input(INPUT_POST, 'playlistDeleteTrack');
$trackAdd = filter_input(INPUT_POST, 'trackAdd');

if ($login) {
    include 'controller/userLogin.php';
} else if ($register) {
    include 'controller/userRegister.php';
}

if (!isLogin()) {
    $action = ($action != 'tracks' ? 'playlists' : 'tracks');
} else if ($playlistAdd) {
    include 'controller/playlistAdd.php';
    return;
} else if ($playlistAddTrack) {
    include 'controller/playlistAddTrack.php';
    return;
} else if ($trackAdd) {
    include 'controller/trackAdd.php';
    return;
}

if (!$action) {
    $action = 'playlists';
}

if (!file_exists('controller/' . $action . '.php')) {
    http_response_code(404);
    return;
}
include 'controller/' . $action . '.php';
