<?php
require_once dirname(__FILE__) . '/../dao/authors.php';
$authors = authorDAO::getAll();
include 'view/authorsView.php';
