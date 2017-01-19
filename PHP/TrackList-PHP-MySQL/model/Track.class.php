<?php

class Track {

    private $id;
    private $title;
    private $author;
    private $duration;
    private $genre;

    function __construct($data, $title = null, $author = null, $duration = null, $genre = null) {
        if (is_int($data) && $title != null && $author != null && $duration != null && $genre != null) {
            $this->id = $data;
            $this->title = $title;
            $this->author = $author;
            $this->genre = $genre;
            $this->duration = $duration;
        } else if (is_array($data)) {
            $this->id = $data['id'];
            $this->title = $data['title'];
            $this->author = $data['author_name'];
            $this->duration = $data['duration'];
            $this->genre = $data['genre'];
        } else {
            $this->id = -1;
            $this->title = 'error';
            $this->author = -1;
            $this->genre = -1;
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
    
    function getGenre() {
        return $this->genre;
    }

    // Calcul du temps d'une musique sous forme mins:secs
    function getStripDuration() {
        $minutes = floor($this->duration / 60);
        $seconds = $this->duration - $minutes * 60;
        if (strlen($seconds) === 0) {
            $seconds = '00';
        } else if (strlen($seconds) === 1) {
            $seconds = '0' . $seconds;
        }
        return $minutes . ':' . $seconds;
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

    function setGenre($genre) {
        $this->genre = $genre;
    }
}
