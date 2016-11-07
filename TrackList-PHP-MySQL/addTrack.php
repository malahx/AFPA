<?php

// Chargement des fonctions divers
require('common.php');

// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
    die();
}

// Récupération des divers variables GET/POST
$trackId = filter_input(INPUT_POST, 'trackId', FILTER_SANITIZE_NUMBER_INT);
$trackTitle = filter_input(INPUT_POST, 'trackTitle', FILTER_SANITIZE_SPECIAL_CHARS);
$trackAuthor = filter_input(INPUT_POST, 'trackAuthor', FILTER_SANITIZE_SPECIAL_CHARS);
$trackDura = filter_input(INPUT_POST, 'trackDura', FILTER_SANITIZE_NUMBER_INT);

// Création du titre
$insert = bdd()->prepare('INSERT INTO `track` VALUES(0, :title, :author, :duration)');
$insert->bindValue(":title", $trackTitle);
$insert->bindValue(":author", $trackAuthor);
$insert->bindValue(":duration", (int) $trackDura);
$insert->execute();
if (!$insert) {
    echo bdd()->errorInfo();
    die();
}

refresh("showAllTracks.php");
