<?php
// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
}

require_once(dirname(__FILE__).'/view.inc.php');

view::head();
view::navbar($action);

// Création du tableau

echo '  <table class="table table-hover">
            <thead>
                <tr>
                    <th>Playlist</th>
                    <th>Description</th>
                    <th class="text-center">Titres</th>
                    <th class="text-right">Nombre de titres</th>
                    <th class="text-right"></th>
                    <th class="text-right"></th>
                </tr>
            </thead>
            <tbody>';

// Création de chaque élement du tableau
foreach ($playlists as $playlist) {
    $onclick = 'onclick="location.href=\'index.php?action=thisPlaylist&playlistId=' . $playlist->getId() . '\'"';
    echo '<tr class="cursor">';
    echo '<td '.$onclick.'>';
    if ($playlist->getJaq()) {
        echo '<img height="25px" class="img-rounded" src="' . $playlist->getJaq() . '">&nbsp;';
    }
    echo $playlist->getName() . '(' . $playlist->getId() . ')' . '</td>';
    echo    '<td '.$onclick.'>' . $playlist->getDesc() . '</td>
             <td '.$onclick.' class="text-center">' . $playlist->getTracksName() . '</td>
             <td '.$onclick.' class="text-right">' . $playlist->getTracksCount() . '</td>
             <td '.$onclick.' class="text-right"></td>
             <td class="text-right"><span title="Modifier" class="glyphicon glyphicon-edit cursor hover" data-toggle="modal" data-target="#playlistModal"></span>&nbsp;<span title="Supprimer" class="glyphicon glyphicon-trash cursor hover" onclick="location.href=\'remPlaylist.php?id=' . $playlist->getId() . '\'"></span></td>
          </tr>';
}

// Fermeture du tableau
echo '      </tbody>
        </table>';

view::modals();
view::footer();
