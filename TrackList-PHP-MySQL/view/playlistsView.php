<?php

require_once(dirname(__FILE__).'/view.inc.php');

view::head();
view::navbar($action);

// Dans le cas où l'utilisateur s'est trompé de mot de passe
$error = filter_input(INPUT_GET, 'error', FILTER_SANITIZE_SPECIAL_CHARS);

if ($error == 'badpwd') {
    echo '<div class="alert alert-warning fade in">'
    . '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
    . '<strong>Login!</strong> Mauvais mot de passe.</div>';

    // Dans le cas où l'utilisateur à voulu créer un utilisateur existant
} elseif ($error == 'baduser') {
    echo '<div class="alert alert-warning fade in">'
    . '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
    . '<strong>Enregistrement!</strong> Utilisateur existant.'
    . '</div>';
}

// Création du tableau

echo '  <table class="table table-hover">
            <thead>
                <tr>
                    <th>Playlist</th>
                    <th>Description</th>
                    <th class="text-center">Utilisateur</th>
                    <th class="text-right">Nombre de titres</th>
                    <th class="text-right"></th>
                </tr>
            </thead>
            <tbody>';

// Création de chaque élement du tableau
foreach ($playlists as $playlist) {
    // Importer une playlist ou ouvrir une playlist ou se logger
    if (isLogin()) {
        if ($playlist->getUserId() == $_SESSION['userid']) {
            $onclick = 'onclick="location.href=\'index.php?action=thisPlaylist&playlistId=' . $playlist->getId() . '\'"';
        } else {
            $onclick = 'onclick="location.href=\'index.php?action=playlistImport&playlistId=' . $playlist->getId() . '\'"';
        }
    } else {
        $onclick = 'data-toggle="modal" data-target="#loginModal"';
    }
    echo '<tr class="cursor" ' . $onclick . '>';
    echo '<td>';
    if ($playlist->getJaq()) {
        echo '<img height="25px" class="img-rounded" src="' . $playlist->getJaq() . '">&nbsp;';
    }
    echo $playlist->getName() . '(' . $playlist->getId() . ')' . '</td>';
    echo '<td>' . $playlist->getDesc() . '</td>
             <td class="text-center">' . $playlist->getUserName() . '</td>
             <td class="text-right">' . $playlist->getTracksCount() . '</td>
             <td class="text-right"></td>
          </tr>';
}

// Fermeture du tableau
echo '      </tbody>
        </table>';

view::modals();
view::footer();