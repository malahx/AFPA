<?php

require_once dirname(__FILE__).'/../dao/user.php';

// Récupération des divers variables GET/POST
$username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_SPECIAL_CHARS);
$email = filter_input(INPUT_POST, 'email', FILTER_SANITIZE_EMAIL);
$password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_SPECIAL_CHARS);

$existUser = userDAO::getByName($username);
if ($existUser == -1) {
    refresh('showAllPlaylists.php?fct=baduser');
}

$user = new User(0, $username, $email, password_hash($password, PASSWORD_DEFAULT));

$userId = userDAO::insert($user);

if ($userid != -1) {
    $_SESSION['userid'] = (int) $userid;
    $_SESSION['username'] = $user->getName();
}
header('Location: index.php');