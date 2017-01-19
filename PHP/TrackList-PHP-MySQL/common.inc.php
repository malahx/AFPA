<?php

require_once 'config.inc.php';
require_once 'dao/playlist.php';

// Démarrage de la session utilisateur
session_start();

// Fonction vérifiant si l'utilisateur est login
function isLogin() {
    return isset($_SESSION['userid']) && isset($_SESSION['username']);
}

// Récupération de la session courante
if (isLogin()) {
    $userid = (int) $_SESSION['userid'];
    $username = $_SESSION['username'];

    // Récupération de la playlist ouverte
    $playlistId = filter_input(INPUT_GET, 'playlistId', FILTER_SANITIZE_NUMBER_INT);

    if (isset($playlistId) || isset($_SESSION['last_Playlist'])) {
        if (isset($playlistId)) {
            $_SESSION['last_Playlist'] = $playlistId;
        } else {
            $playlistId = $_SESSION['last_Playlist'];
        }
        $playlist = playlistDAO::getById($playlistId);
        if (empty($playlist) || $playlist === -1) {
            unset($_SESSION['last_Playlist']);
            header('Location: index.php');
        }
    }
}