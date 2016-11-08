<?php

// Chargement des fonctions divers
require('common.php');

// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
    die();
}

// Récupération des divers variables GET/POST
$playlistId = filter_input(INPUT_POST, 'playlistId', FILTER_SANITIZE_NUMBER_INT);

// Récupération de la playlist à dupliquer
$select = bdd()->prepare('SELECT * FROM `playlist-header` h WHERE h.id = :id');
$select->bindValue(":id", (int) $playlistId);
$select->execute();
if (!$select) {
    echo bdd()->errorInfo();
    die();
}
$data = $select->fetch();
if (empty($data)) {
    refresh();
}
$playlist = new Playlist($data);

// Création de la playlist
$insert = bdd()->prepare('INSERT INTO `playlist-header` VALUES(0, :name, :desc, :jaq, :userid)');
$insert->bindValue(":name", $playlist->getName());
$insert->bindValue(":desc", $playlist->getDesc());
$insert->bindValue(":desc", $playlist->getJaq());
$insert->bindValue(":userid", (int) $userid);
$insert->execute();
if (!$insert) {
    echo bdd()->errorInfo();
    die();
}
$newPlaylistId = bdd()->lastInsertId();

// Ajout de chaques titres
$select_track = bdd()->prepare('SELECT * FROM `playlist` p WHERE p.playlistid = :id');
$select_track->bindValue(":id", (int) $playlistId);
$select_track->execute();
if (!$select_track) {
    echo bdd()->errorInfo();
    die();
}
while ($data = $select_track->fetch()) {
    $insert = bdd()->prepare('INSERT INTO `playlist` VALUES(:playlistid, :trackid)');
    $insert->bindValue(":playlistid", $newPlaylistId);
    $insert->bindValue(":trackid", $data['trackid']);
    $insert->execute();
    if (!$insert) {
        echo bdd()->errorInfo();
        die();
    }
}

refresh('showPlaylistTracks.php?playlistId=' . $newPlaylistId);
