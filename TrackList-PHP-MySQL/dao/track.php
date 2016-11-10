<?php

require_once(dirname(__FILE__) . '/dao.inc.php');
require_once(dirname(__FILE__) . '/../model/Track.class.php');

class trackDAO {

    static function getAll() {
        $bdd = dao::connect();
        $select = $bdd->prepare('SELECT t.title, t.duration, t.id,  a.author_name, g.genre 
                                    FROM track t
                                    INNER JOIN author a 
                                    ON t.author_id = a.id
                                    INNER JOIN genre g
                                    ON t.genre_id = g.id');
        $select->execute();
        return self::toArray($select);
    }

    static function getFromPlaylist($playlistId) {
        $bdd = dao::connect();
        $select = $bdd->prepare('SELECT t.id, t.title, t.duration, a.author_name, g.genre 
                                    FROM `playlist` p
                                    INNER JOIN `track` t
                                    ON t.id = p.trackid
                                    INNER JOIN author a 
                                    ON t.author_id = a.id
                                    INNER JOIN genre g
                                    ON t.genre_id = g.id
                                    WHERE p.playlistid = :playlistId');
        $select->bindValue(":playlistId", $playlistId);
        $select->execute();
        return self::toArray($select);
    }

    private static function toArray($select) {
        if (!$select) {
            return -1;
        }
        $tracks = array();
        while ($data = $select->fetch()) {
            $track = new Track($data);
            array_push($tracks, $track);
        }
        return $tracks;
    }

    static function insert($track) {
        $bdd = dao::connect();
        $insert = bdd()->prepare('INSERT INTO `track` VALUES(0, :title, :duration, :author_id, :genre_id)');
        $insert->bindValue(":title", $track->getTitle());
        $insert->bindValue(":author_id", (int) $track->getAuthor());
        $insert->bindValue(":duration", (int) $track->getDuration());
        $insert->bindValue(":genre_id", (int) $track->getGenre());
        $insert->execute();
        if (!$insert) {
            return -1;
        }
        return $bdd->lastInsertId();
    }

    static function delete($trackId) {
        $bdd = dao::connect();
        $delete = $bdd->prepare('DELETE FROM track WHERE id = :trackid');
        $delete->bindValue(":trackid", (int) $trackId);
        $delete->execute();
    }

}
