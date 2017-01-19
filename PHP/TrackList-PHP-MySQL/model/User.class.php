<?php

class User {

    private $id;
    private $name;
    private $email;
    private $password;

    function __construct($data, $name = null, $email = null, $password = null) {
        if (is_int($data) && $name != null && $email != null && $password != null) {
            $this->id = $data;
            $this->name = $name;
            $this->email = $email;
            $this->password = $password;
        } else if (is_array($data)) {
            $this->id = (int) $data['id'];
            $this->name = $data['username'];
            $this->email = $data['email'];
            $this->password = $data['password'];
        } else {
            $this->id = -1;
            $this->name = '';
            $this->email = '';
            $this->password = '';
        }
    }

    function getId() {
        return $this->id;
    }

    function getName() {
        return $this->name;
    }

    function getEmail() {
        return $this->email;
    }

    function getPassword() {
        return $this->password;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setName($name) {
        $this->name = $name;
    }

    function setEmail($email) {
        $this->email = $email;
    }

    function setPassword($password) {
        $this->password = $password;
    }

}
