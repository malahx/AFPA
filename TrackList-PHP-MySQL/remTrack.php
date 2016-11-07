<?php

// Chargement des fonctions divers
require('common.php');

// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
    die();
}

$trackid = filter_input(INPUT_GET, 'id', FILTER_SANITIZE_NUMBER_INT);

if (!empty($trackid)) {
        $insert = bdd()->prepare('DELETE FROM track WHERE id = :trackid');
        $insert->bindValue(":trackid", (int) $trackid);
        $insert->execute();
}
refresh('showAllTracks.php');