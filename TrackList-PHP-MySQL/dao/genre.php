<?php

require_once(dirname(__FILE__).'/dao.inc.php');
require_once(dirname(__FILE__).'/../model/Genre.class.php');

class genreDAO {

    static function getAll() {
        $bdd = dao::connect();
        $select = $bdd->prepare('SELECT * FROM genre');
        $select->execute();
        return self::toArray($select);
    }

    private static function toArray($select) {
        if (!$select) {
            return -1;
        }
        $genres = array();
        while ($data = $select->fetch()) {
            $genre = new Genre($data);
            array_push($genres, $genre);
        }
        return $genres;
    }

    static function insert($genre) {
        $bdd = dao::connect();
        
        // Création du genre
        $insert = $bdd->prepare('INSERT INTO `genre` (genre, descr, genre_url) VALUES (:genre, :desc, :url)');
        $insert->bindValue("desc", $genre->getDesc());
        $insert->bindValue("genre", $genre->getName());
        $insert->bindValue("url", $genre->getUrl());
        $insert->execute();
        if (!$insert) {
            return -1;
        }

        return $bdd->lastInsertId();
    }

}
