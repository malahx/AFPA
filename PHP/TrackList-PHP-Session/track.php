<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class Track {
    private $id;
    private $title;
    private $author;
    private $year;
    private $length;
    function __construct($id, $title, $author, $year, $length) {
        $this->id = $id;
        $this->title = $title;
        $this->author = $author;
        $this->year = $year;
        $this->length = $length;
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

