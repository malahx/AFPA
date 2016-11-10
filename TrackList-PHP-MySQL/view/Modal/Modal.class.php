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
}
