<?php

require_once dirname(__FILE__) . '/Input.class.php';

class Modal {
    
    private $id;
    private $text;
    private $action;
    private $glyphicon;
    private $inputs;
    
    function __construct($id, $text, $action, $glyphicon, $inputs) {
        $this->id = $id;
        $this->text = $text;
        $this->action = $action;
        $this->glyphicon = $glyphicon;
        $this->inputs = $inputs;
    }    
    
    function renderInputs() {
        $render = '';
        foreach ($this->inputs as $input) {   
            $render .= $input->getRender();
        }        
        return $render;
    }
    
    function getId() {
        return $this->id;
    }

    function getText() {
        return $this->text;
    }

    function getAction() {
        return $this->action;
    }

    function getGlyphicon() {
        return $this->glyphicon;
    }

    function getInputs() {
        return $this->inputs;
    }

    function setId($id) {
        $this->id = $id;
    }

    function setText($text) {
        $this->text = $text;
    }

    function setAction($action) {
        $this->action = $action;
    }

    function setGlyphicon($glyphicon) {
        $this->glyphicon = $glyphicon;
    }

    function setInputs($inputs) {
        $this->inputs = $inputs;
    }
}
