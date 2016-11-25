<?php

// Chargement des fonctions divers
require_once 'common.inc.php';

$action = filter_input(INPUT_GET, 'action', FILTER_SANITIZE_SPECIAL_CHARS);
$login = filter_input(INPUT_POST, 'login');
$register = filter_input(INPUT_POST, 'register');
$playlistAdd = filter_input(INPUT_POST, 'playlist');
$playlistAddTrack = filter_input(INPUT_POST, 'playlistAddTrack');
$playlistDeleteTrack = filter_input(INPUT_POST, 'playlistDeleteTrack');
$trackAdd = filter_input(INPUT_POST, 'trackAdd');

if (isset($login)) {
    include 'controller/userLogin.php';
} else if (isset($register)) {
    include 'controller/userRegister.php';
}

if (!isLogin()) {
    $action = ($action != 'tracks' ? 'playlists' : 'tracks');
} else if (isset($playlistAdd)) {
    include 'controller/playlistAdd.php';
    return;
} else if (isset($playlistAddTrack)) {
    include 'controller/playlistAddTrack.php';
    return;
} else if (isset($trackAdd)) {
    include 'controller/trackAdd.php';
    return;
}

if (!isset($action)) {
    $action = 'playlists';
}

if (!file_exists('controller/' . $action . '.php')) {
    http_response_code(404);
    return;
}
include 'controller/' . $action . '.php';
