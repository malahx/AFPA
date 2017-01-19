<?php

// Redirection pour un utilisateur non login
if (!isLogin()) {
    header('Location: index.php');
}

require_once dirname(__FILE__).'/../dao/genre.php';

// Récupération des divers variables GET/POST

$genreId = filter_input(INPUT_POST, 'genreId', FILTER_SANITIZE_NUMBER_INT);
$genreName = filter_input(INPUT_POST, 'genreName', FILTER_SANITIZE_SPECIAL_CHARS);
$genreDesc = filter_input(INPUT_POST, 'desc', FILTER_SANITIZE_SPECIAL_CHARS);
$genreURL = filter_input(INPUT_POST, 'url', FILTER_VALIDATE_URL);

$genre = new Genre(!empty($genreId) ? $genreId : 0, $genreName, $genreDesc, $genreURL);
genreDAO::insert($genre);
header('Location: index.php?action=genres');