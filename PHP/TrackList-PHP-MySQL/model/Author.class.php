<?php

class Author {

    private $id;
    private $name;

    function __construct($data, $name = null) {
        if (is_int($data) && $name != null) {
            $this->id = $data;
            $this->name = $name;
        } else if (is_array($data)) {
            $this->id = (int) $data['id'];
            $this->name = $data['author_name'];
        } else {
            $this->id = -1;
            $this->name = '';
        }
    }

    function getId() {
        return $this->id;
    }

    function getName() {
        return $this->name;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setName($name) {
        $this->name = $name;
    }

}
