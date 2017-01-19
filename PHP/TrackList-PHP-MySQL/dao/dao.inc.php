<?php

// Chargement de la configuration
require_once(dirname(__FILE__).'/../config.inc.php');

use \PDO as PDO;

class dao {
    static function connect() {
        return new PDO(BDD_DSN, BDD_USERNAME, BDD_PASSWORD);
    }
}
