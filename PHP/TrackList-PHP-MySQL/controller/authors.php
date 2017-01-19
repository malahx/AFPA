<?php
require_once dirname(__FILE__) . '/../dao/author.php';
$authors = authorDAO::getAll();
include 'view/authorsView.php';
