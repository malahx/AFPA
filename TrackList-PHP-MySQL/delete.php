<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
require ("config.php");
session_start();
if (!isset($_SESSION['userid'])) {
    session_destroy();
    header('Location: index.php');
    die();
}
$id = filter_input(INPUT_GET, 'id', FILTER_SANITIZE_SPECIAL_CHARS);
if ($id != null) {
    $bdd = new PDO(BDD_DSN, BDD_USERNAME, BDD_PASSWORD);
    if (!$bdd) {
        die('Connexion impossible : ' . mysql_error());
    }
    // Suppression d'un musique
    $query = $bdd->prepare('DELETE FROM tracks WHERE id = :id');
    $query->bindValue(":id", $id);
    $query->execute();
}
header('Location: tracklist.php');