<?php

// Chargement des fonctions divers
require('common.php');

// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
    die();
}

// Récupération des divers variables GET/POST

$genreName = filter_input(INPUT_POST, 'genreName', FILTER_SANITIZE_SPECIAL_CHARS);
$genreDesc = filter_input(INPUT_POST, 'desc', FILTER_SANITIZE_SPECIAL_CHARS);
$genreURL = filter_input(INPUT_POST, 'url', FILTER_VALIDATE_URL);

// Création du titre
$insert = bdd()->prepare('INSERT INTO `genre` (genre, descr, genre_url) VALUES (:genre, :desc, :url)');
$insert->bindValue("desc", $genreDesc);
$insert->bindValue("genre", $genreName);
$insert->bindValue("url", $genreURL);

$insert->execute();
if (!$insert) {
    echo bdd()->errorInfo();
    die();
}

refresh("showGenres.php");