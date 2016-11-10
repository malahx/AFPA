<?php
require_once dirname(__FILE__) . '/../dao/track.php';
$tracks = trackDAO::getAll();
include 'view/tracksView.php';
