<?php

require_once dirname(__FILE__) . '/dao.inc.php';
require_once dirname(__FILE__) . '/../model/Author.class.php';

class authorDAO {

    static function getAll() {
        $bdd = dao::connect();
        $select = $bdd->prepare('SELECT * FROM author');
        $select->execute();
        return self::toArray($select);
    }

    private static function toArray($select) {
        if (!$select) {
            return -1;
        }
        $authors = array();
        while ($data = $select->fetch()) {
            $author = new Author($data);
            array_push($authors, $author);
        }
        return $authors;
    }

    static function insert($author) {
        $bdd = dao::connect();

        // Création du genre
        $insert = $bdd->prepare('INSERT INTO `author` (author_name) VALUES (:name)');
        $insert->bindValue("name", $author->getName());
        $insert->execute();
        if (!$insert) {
            return -1;
        }

        return $bdd->lastInsertId();
    }

}
