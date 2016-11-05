<?php

// Objet de chaque chanson
class Track {
    private $id;
    private $title;
    private $author;
    private $year;
    private $length;
    function __construct($data, $title = null, $author = null, $duration = null) {
        if (is_int($data) && $title != null && $author != null && $duration != null) {
            $this->id = $data;
            $this->title = $title;
            $this->author = $author;
            $this->duration = $duration;
        } else if (is_array($data)) {
            $this->id = $data['id'];
            $this->title = $data['title'];
            $this->author = $data['author'];
            $this->duration = $data['duration'];
        } else {
            $this->id = -1;
            $this->title = 'error';
            $this->author = 'error';
            $this->duration = -1;
        }
    }
    function getId() {
        return $this->id;
    }

    function getTitle() {
        return $this->title;
    }

    function getAuthor() {
        return $this->author;
    }

    function getDuration() {
        return $this->duration;
    }
    
    // Calcul du temps d'une musique sous forme mins:secs
    function getStripDuration() {
        $minutes = floor($this->duration/ 60);
        $seconds = $this->duration - $minutes * 60;
        if (strlen($seconds) === 0) {
            $seconds = '00';
        } else if (strlen($seconds) === 1) {
            $seconds = '0'.$seconds;
        }
        return $minutes.':'.$seconds;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setTitle($title) {
        $this->title = $title;
    }

    function setAuthor($author) {
        $this->author = $author;
    }

    function setDuration($duration) {
        $this->duration = $duration;
    }
}

