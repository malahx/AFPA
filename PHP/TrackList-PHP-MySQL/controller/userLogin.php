<?php

require_once dirname(__FILE__).'/../dao/user.php';

// Récupération des divers variables GET/POST
$username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_SPECIAL_CHARS);
$password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_SPECIAL_CHARS);

if ($username != null && $password != null) {

    // Récupération de l'utilisateur entré
    $user = userDAO::getByName($username);
    if (empty($user)) {
        $error = 'baduser';
    } else {
        if ($user == -1) {
            refresh();
        }
        if (password_verify($password, $user->getPassword())) {
            $_SESSION['userid'] = (int) $user->getId();
            $_SESSION['username'] = $user->getName();
        } else {
            $error = 'badpwd';
        }
    }
}
header('Location: index.php');