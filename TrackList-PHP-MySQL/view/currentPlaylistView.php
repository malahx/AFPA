<?php
// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
}

if (!isset($playlistId) || !isset($playlist)) {
    refresh();
}

require_once(dirname(__FILE__) . '/view.inc.php');

view::head();
view::navbar($action);

// Affichage de la jaquette et de la description d'une playlist
echo '<div class="container text-center"><h2>';
if ($playlist->getJaq()) {
    echo '<img height="100px" class="img-rounded" src="' . $playlist->getJaq() . '">&nbsp;';
}
echo $playlist->getDesc();
echo '</h2></div>';

// Création du tableau
echo ' <form action="index.php" method="POST">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th></th>
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th class="text-center">Durée</th>
                        <th class="text-right"></th>
                    </tr>
                </thead>
                <tbody>';

// Création de chaque élement du tableau
foreach ($tracks as $track) {
    echo '<tr class="cursorDefault">';
    if (isset($playlistId)) {
        echo '<td><input type="checkbox" name="remTrackFromPlaylist[]" value="' . $track->getId() . '"></td>';
    }
    echo '<td>' . $track->getTitle() . '(' . $track->getId() . ')</td>
             <td>' . $track->getAuthor() . '</td>
             <td class="text-center">' . $track->getStripDuration() . '</td>
             <td class="text-right"><span title="Modifier" class="glyphicon glyphicon-edit cursor hover" data-toggle="modal" data-target="#trackModal"></span>&nbsp;<span title="Supprimer" class="glyphicon glyphicon-trash cursor hover" onclick="location.href=\'remTrackFromPlaylist.php?id=' . $track->getId() . '\'"></span></td>
        </tr>';
}

// Fermeture du tableau
echo '      </tbody>
        </table>
        <div class="text-center">
            <button type="submit" name="playlistAddTrack" class="btn btn-lg btn-block btn-danger">
                <span class="glyphicon glyphicon-chevron-left"></span> supprimé les titres sélectionnés de la playlist
            </button>
        </div>
    </form>';

view::modals();
view::footer();