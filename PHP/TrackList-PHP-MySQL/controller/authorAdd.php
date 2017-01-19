<?php

// Redirection pour un utilisateur non login
if (!isLogin()) {
    header('Location: index.php');
}

require_once dirname(__FILE__).'/../dao/author.php';

// Récupération des divers variables GET/POST

$authorId = filter_input(INPUT_POST, 'authorId', FILTER_SANITIZE_NUMBER_INT);
$authorName = filter_input(INPUT_POST, 'authorName', FILTER_SANITIZE_SPECIAL_CHARS);

$author = new Author(!empty($authorId) ? $authorId : 0, $authorName);
authorDAO::insert($author);
header('Location: index.php?action=authors');