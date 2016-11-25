<?php

require_once(dirname(__FILE__).'/dao.inc.php');
require_once(dirname(__FILE__).'/../model/Playlist.class.php');
require_once(dirname(__FILE__).'/track.php');

class playlistDAO {

    static function getAll() {
        $bdd = dao::connect();
        $select = $bdd->prepare('SELECT h.id, h.name, h.desc, h.jaqurl, h.userid, u.username 
                                    FROM `playlist_header` h
                                    INNER JOIN `user` u
                                    ON u.id = h.userid');
        $select->execute();
        return self::toArray($select);
    }

    static function getAllByUserId($playlistId) {
        $bdd = dao::connect();
        // requete manque l'user a revoir concat
        $select = $bdd->prepare('SELECT h.id, h.name, h.desc, h.jaqurl, h.userid, u.username
                                    FROM `playlist_header` h 
                                    INNER JOIN `user` u
                                    ON u.id = h.userid
                                    WHERE h.userid = :userid');
        $select->bindValue(":userid", $playlistId);
        $select->execute();
        return self::toArray($select);
    }

    private static function toArray($select) {
        if (!$select) {
            return -1;
        }
        $playlists = array();
        while ($data = $select->fetch()) {
            $playlist = new Playlist($data);
            $tracks = trackDAO::getFromPlaylist($playlist->getId());
            $playlist->setTracks($tracks);
            array_push($playlists, $playlist);
        }
        return $playlists;
    }

    static function getById($playlistId) {
        $bdd = dao::connect();
        $select = $bdd->prepare('SELECT * FROM `playlist_header` h WHERE h.id = :id');
        $select->bindValue(":id", (int) $playlistId);
        $select->execute();
        if (!$select) {
            return -1;
        }
        $data = $select->fetch();
        if (empty($data)) {
            return -1;
        }
        $playlist = new Playlist($data);
        return $playlist;
    }
    
    static function exists($playlistId) {
        $playlist = self::getById($playlistId);
        return !empty($playlist) && $playlist != -1;
    }

    static function insert($playlist) {
        $bdd = dao::connect();
        // Création de la playlist
        $insert = $bdd->prepare('INSERT INTO `playlist_header` VALUES(0, :name, :desc, :jaq, :userid)');
        $insert->bindValue(":name", $playlist->getName());
        $insert->bindValue(":desc", $playlist->getDesc());
        $insert->bindValue(":userid", (int) $playlist->getUserId());
        $insert->bindValue(":jaq", $playlist->getJaq());
        $insert->execute();
        if (!$insert) {
            return -1;
        }
        return $bdd->lastInsertId();
    }

    static function insertTrack($playlistId, $trackId) {
        $bdd = dao::connect();
        $insert = $bdd->prepare('INSERT INTO `playlist` VALUES(:playlistid, :trackid)');
        $insert->bindValue(":playlistid", (int) $playlistId);
        $insert->bindValue(":trackid", (int) $trackId);
        $insert->execute();
        if (!$insert) {
            return -1;
        }
        return $bdd->lastInsertId();
    }

    static function delete($playlistId) {
        $bdd = dao::connect();
        $delete = $bdd->prepare('DELETE FROM `playlist_header` WHERE id = :playlistid');
        $delete->bindValue(":playlistid", (int) $playlistId);
        $delete->execute();
    }

    static function deleteTracks($playlistId, $trackIds) {
        $bdd = dao::connect();
        if (is_array($trackIds)) {
            foreach ($trackIds as $trackId) {
                $delete = $bdd->prepare('DELETE FROM playlist WHERE playlistid = :playlistid AND trackid = :trackid');
                $delete->bindValue(":playlistid", (int) $playlistId);
                $delete->bindValue(":trackid", (int) $trackId);
                $delete->execute();
            }
            return;
        }
        $insert = $bdd->prepare('DELETE FROM playlist WHERE playlistid = :playlistid AND trackid = :trackid');
        $insert->bindValue(":playlistid", (int) $playlistId);
        $insert->bindValue(":trackid", (int) $trackIds);
        $insert->execute();
    }
}
