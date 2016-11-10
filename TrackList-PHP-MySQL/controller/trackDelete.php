<?php

// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
}

require_once dirname(__FILE__).'/../dao/playlist.php';

$trackId = filter_input(INPUT_GET, 'id', FILTER_SANITIZE_NUMBER_INT);

if (!empty($trackId)) {
    playlistDAO::delete($trackId);
}
header('Location: index.php?action=tracks');