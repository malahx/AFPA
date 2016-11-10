<?php

class Playlist {

    private $id;
    private $name;
    private $userName;
    private $userId;
    private $desc;
    private $jaq;
    private $tracks;

    function __construct($data, $userName = null, $userId = null, $name = null, $desc = null, $jaq = null) {
        if (is_int($data) && $userName != null && $userId != null && $name != null && $desc != null) {
            $this->id = $data;
            $this->userName = $userName;
            $this->userId = $userId;
            $this->name = $name;
            $this->desc = $desc;
            $this->jaq = $jaq;
            $this->tracks = array();
        } else if (is_array($data)) {
            $this->id = $data['id'];
            $this->userName = (isset($data['username']) ? $data['username'] : '');
            $this->userId = (isset($data['userid']) ? $data['userid'] : '');
            $this->name = $data['name'];
            $this->desc = $data['desc'];
            $this->jaq = $data['jaqurl'];
            $this->tracks = array();
        } else {
            $this->id = -1;
            $this->userName = '';
            $this->userId = -1;
            $this->user = '';
            $this->desc = '';
            $this->jaq = '';
            $this->tracks = array();
        }
    }

    function getTracks() {
        return $this->tracks;
    }

    function getTracksCount() {
        return count($this->tracks);
    }

    function getTracksName() {
        $tracks = $this->tracks;
        $tracksName = '';
        foreach ($tracks as $track) {
            $tracksName = $tracksName . ($tracksName != '' ? ',' : '') . $track->getTitle();
        }
        return $tracksName != '' ? $tracksName : 'Aucun';
    }

    function getId() {
        return $this->id;
    }

    function getName() {
        return $this->name;
    }

    function getJaq() {
        return $this->jaq;
    }

    function getDesc() {
        return $this->desc;
    }

    function getUserName() {
        return $this->userName;
    }

    function getUserId() {
        return $this->userId;
    }

    function setTracks($tracks) {
        $this->tracks = $tracks;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setName($name) {
        $this->name = $name;
    }

    function setUser($user) {
        $this->user = $user;
    }

    function setJaq($jaq) {
        $this->jaq = $jaq;
    }

    function setDesc($desc) {
        $this->desc = $desc;
    }

    function setUserName($userName) {
        $this->userName = $userName;
    }

    function setUserId($userId) {
        $this->userId = $userId;
    }

}
