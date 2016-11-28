<?php

// Redirection pour un utilisateur non login
if (!isLogin()) {
    header('Location: index.php');
}

require_once dirname(__FILE__).'/../dao/track.php';

// Récupération des divers variables GET/POST
$trackId = filter_input(INPUT_POST, 'trackId', FILTER_SANITIZE_NUMBER_INT);
$trackTitle = filter_input(INPUT_POST, 'trackTitle', FILTER_SANITIZE_SPECIAL_CHARS);
$trackAuthor = filter_input(INPUT_POST, 'trackAuthor', FILTER_SANITIZE_NUMBER_INT);
$trackDura = filter_input(INPUT_POST, 'trackDura', FILTER_SANITIZE_NUMBER_INT);
$trackGenre = filter_input(INPUT_POST, 'trackGenre', FILTER_SANITIZE_NUMBER_INT);

$track = new Track(!empty($trackId) ? $trackId : 0, $trackTitle, $trackAuthor, $trackDura, $trackGenre);
trackDAO::insert($track);
header('Location: index.php?action=tracks');