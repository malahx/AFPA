<?php

class Input {

    private $type, $id, $text, $glyphicon, $data;

    function __construct($type, $id, $text, $glyphicon, $data = null) {
        $this->type = $type;
        $this->id = $id;
        $this->text = $text;
        $this->glyphicon = $glyphicon;
        $this->data = $data;
    }

    function getRender() {
        switch ($this->type) {
            case 'select':
                $render = ' <div class="form-group">
                                <label class="control-label" for="' . $this->id . '"><span class="glyphicon glyphicon-' . $this->glyphicon . '"></span>' . $this->text . ' :</label>
                                <select id="' . $this->id . '" class="form-control" name="' . $this->id . '">';
                while ($this->data) {
                        $render .= '<option value="' . $this->data['id'] . '">' . $this->data['text'] . '</option>';

                }
                $render .= '    </select>
                            </div>';
                break;
            case 'file':
                $render = ' <div class="form-group">
                                <label class="control-label" for="' . $this->id . '"><span class="glyphicon glyphicon-' . $this->glyphicon . '"></span> ' . $this->text . ' :</label>
                                <input type="' . $this->type . '" name="' . $this->id . '" accept="' . $this->data . '">
                            </div>';
                break;
            default:
                $render = ' <div class="form-group">
                                <label class="control-label" for="' . $this->id . '"><span class="glyphicon glyphicon-' . $this->glyphicon . '"></span> ' . $this->text . ' :</label>
                                <input id="' . $this->id . '" class="form-control" type="' . $this->type . '" name="' . $this->id . '" placeholder="' . $this->text . '" required pattern=".{1,120}">
                            </div>';
                break;
        }
        return $render;
    }
}
