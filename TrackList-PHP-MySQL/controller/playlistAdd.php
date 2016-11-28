<?php

// Redirection pour un utilisateur non login
if (!isLogin()) {
    header('Location: index.php');
}

require_once dirname(__FILE__).'/../dao/playlist.php';

// Récupération des divers variables GET/POST
$playlistName = filter_input(INPUT_POST, 'playlistName', FILTER_SANITIZE_SPECIAL_CHARS);
$playlistDesc = filter_input(INPUT_POST, 'playlistDesc', FILTER_SANITIZE_SPECIAL_CHARS);
$playlistJaq = (isset($_FILES['playlistJaq']) ? $_FILES['playlistJaq'] : null);
$uploadFile = null;

// Enregistrement d'une jaquette possible
if (!empty($playlistJaq)) {

    // Nom du fichier avec un nom unique
    $uploadFile = UPLOAD . uniqid('JAQ_') . '.' . pathinfo($playlistJaq['name'], PATHINFO_EXTENSION);

    // Vérification si le fichier existe
    if (file_exists($uploadFile)) {
        header('Location: index.php');
    }

    // Déplacement du fichier dans le bon dossier
    if (!move_uploaded_file($playlistJaq['tmp_name'], $uploadFile)) {
        $uploadFile = null;
    }
}
$playlist = new Playlist(0, $username, $userid, $playlistName, $playlistDesc, $uploadFile);
$playlistId = playlistDAO::insert($playlist);
header('Location: index.php?action=thisPlaylist&playlistId=' . $playlistId);