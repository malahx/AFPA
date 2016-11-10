<?php
require_once dirname(__FILE__) . '/../dao/genre.php';
$genres = genreDAO::getAll();
include 'view/genresView.php';
