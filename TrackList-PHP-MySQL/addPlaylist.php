<?php

// Chargement des fonctions divers
require('common.php');

// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
    die();
}

// Récupération des divers variables GET/POST
$playlistName = filter_input(INPUT_POST, 'playlistName', FILTER_SANITIZE_SPECIAL_CHARS);
$playlistDesc = filter_input(INPUT_POST, 'playlistDesc', FILTER_SANITIZE_SPECIAL_CHARS);
$playlistJaq = (isset($_FILES['playlistJaq']) ? $_FILES['playlistJaq'] : null);

// Création de la playlist
$insert = bdd()->prepare('INSERT INTO `playlist-header` VALUES(0, :name, :desc, :jaq, :userid)');
$insert->bindValue(":name", $playlistName);
$insert->bindValue(":desc", $playlistDesc);
$insert->bindValue(":userid", (int) $userid);

// Enregistrement d'une jaquette possible
if (isset($playlistJaq)) {

    // Nom du fichier avec un nom unique
    $uploadFile = UPLOAD . uniqid('JAQ_') . '.' . pathinfo($playlistJaq['name'], PATHINFO_EXTENSION);

    // Vérification si le fichier existe
    if (file_exists($uploadFile)) {
        refresh();
    }

    // Déplacement du fichier dans le bon dossier
    if (move_uploaded_file($playlistJaq['tmp_name'], $uploadFile)) {
        $insert->bindValue(":jaq", $uploadFile);
    } else {
        $insert->bindValue(":jaq", null);
    }
} else {
    $insert->bindValue(":jaq", null);
}

$insert->execute();
if (!$insert) {
    echo bdd()->errorInfo();
    die();
}

$playlistId = bdd()->lastInsertId();

refresh('showPlaylistTracks.php?playlistId=' . $playlistId);
