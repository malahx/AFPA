<?php

class Playlist {
    private $id;
    private $name;
    private $user;
    private $desc;
    function __construct($data, $user = null, $name = null, $desc = null, $tracks = null, $jaq = null) {
        if (is_int($data) && $user != null && $name != null && $desc != null && $tracks != null) {
            $this->id = $data;
            $this->user = $user;
            $this->name = $name;
            $this->desc = $desc;
            $this->tracks = $tracks;
            $this->jaq = $jaq;
        } else if (is_array($data)) {
            $this->id = $data['id'];
            $this->user = (isset($data['username']) ? $data['username'] : $user);
            $this->name = $data['name'];
            $this->desc = $data['desc'];
            $this->tracks = (isset($data['GROUP_CONCAT(p.trackid)']) ? $data['GROUP_CONCAT(p.trackid)'] : null);
            $this->desc = $data['desc'];
            $this->jaq = $data['jaqurl'];
        } else {
            $this->id = -1;
            $this->name = 'error';
            $this->user = 'error';
            $this->desc = 'error';
            $this->tracks = null;
            $this->jaq = null;
        }
    }
    function getId() {
        return $this->id;
    }

    function getName() {
        return $this->name;
    }

    function getUser() {
        return $this->user;
    }

    function getJaq() {
        return $this->jaq;
    }
    
    function getDesc() {
        return $this->desc;
    }
    
    function getTracks() {
        return $this->tracks;
    }
    
   function getTracksCount() {
        return $this->tracks;
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

    function setTracks($tracks) {
        $this->tracks = $tracks;
    }
}