<?php

// Objet de chaque chanson
class Track {
    private $id;
    private $title;
    private $author;
    private $year;
    private $length;
    function __construct($data, $title = null, $author = null, $year = null, $length = null) {
        if (is_int($data) && $title != null && $author != null && $year != null && $length != null) {
            $this->id = $data;
            $this->title = $title;
            $this->author = $author;
            $this->year = $year;
            $this->length = $length;
        } else if (is_array($data)) {
            $this->id = $data['id'];
            $this->title = $data['title'];
            $this->author = $data['author'];
            $this->year = $data['year'];
            $this->length = $data['length'];
        } else {
            $this->id = -1;
            $this->title = 'error';
            $this->author = 'error';
            $this->year = -1;
            $this->length = -1;
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

    function getYear() {
        return $this->year;
    }

    function getLength() {
        return $this->length;
    }
    
    // Calcul du temps d'une musique sous forme mins:secs
    function getDuration() {
        $minutes = floor($this->length/ 60);
        $seconds = $this->length - $minutes * 60;
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

    function setYear($year) {
        $this->year = $year;
    }

    function setLength($length) {
        $this->length = $length;
    }
}

