<?php

class Genre {
    private $id;
    private $name;
    private $desc;
    private $url;
    function __construct($data, $name = null, $desc = null, $url = null) {
        if (is_int($data) && $name != null && $desc != null && $url != null) {
            $this->id = $data;
            $this->name = $name;
            $this->desc = $desc;
            $this->url = $url;
        } else if (is_array($data)) {
            $this->id = (int) $data['id'];
            $this->name = $data['genre'];
            $this->desc = $data['descr'];
            $this->url = $data['genre_url'];
        } else {
            $this->id =  -1;
            $this->name = '';
            $this->desc = '';
            $this->url = '';
        }
    }
    function getId() {
        return $this->id;
    }

    function getName() {
        return $this->name;
    }

    function getDesc() {
        return $this->desc;
    }

    function getUrl() {
        return $this->url;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setName($name) {
        $this->name = $name;
    }

    function setDesc($desc) {
        $this->desc = $desc;
    }

    function setUrl($url) {
        $this->url = $url;
    }
}