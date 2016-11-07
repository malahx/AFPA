<?php

// Chargement des fonctions divers
require('common.php');

// Récupération des divers variables GET/POST
$username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_SPECIAL_CHARS);
$password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_SPECIAL_CHARS);

if ($username != null && $password != null) {

    // Récupération de l'utilisateur entré
    $select = bdd()->prepare('SELECT id, password FROM user WHERE username = :username');
    $select->bindValue(":username", $username);
    $select->execute();
    if (!$select) {
        echo bdd()->errorInfo();
        die();
    }

    // Vérification du mot de passe de l'utilisateur
    while ($data = $select->fetch()) {
        if (password_verify($password, $data['password'])) {
            $userid = $data['id'];
            break;
        } else {
            refresh('showAllPlaylists.php?fct=badpwd');
            die();
        }
    }
    // Enregistrement de la session
    if (isset($userid)) {
        $_SESSION['userid'] = (int) $userid;
        $_SESSION['username'] = $username;
    }
}
refresh();
