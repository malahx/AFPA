<?php

// Chargement des fonctions divers
require('common.php');

// Récupération des divers variables GET/POST
$username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_SPECIAL_CHARS);
$email = filter_input(INPUT_POST, 'email', FILTER_SANITIZE_EMAIL);
$password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_SPECIAL_CHARS);

// Récupération de l'utilisateur entré
$select = bdd()->prepare('SELECT id, password FROM user WHERE username = :username');
$select->bindValue(":username", $username);
$select->execute();
if (!$select) {
    echo bdd()->errorInfo();
    die();
}

// Vérification si l'utilisateur existe déjà
while ($data = $select->fetch()) {
    refresh('showAllPlaylists.php?fct=baduser');
    die();
}

// Création d'un nouvel utilisateur si il n'existait pas
$insert = bdd()->prepare('INSERT INTO user VALUES(0, :username, :email, :password)');

// Chiffrage du mot de passe
$pwHash = password_hash($password, PASSWORD_DEFAULT);

$insert->bindValue(":username", $username);
$insert->bindValue(":email", $email);
$insert->bindValue(":password", $pwHash);
$insert->execute();

$userid = bdd()->lastInsertId();

// Login de l'utilisateur enregistré
if (isset($userid)) {
    $_SESSION['userid'] = (int) $userid;
    $_SESSION['username'] = $username;
}
refresh();
