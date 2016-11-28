<?php

require_once(dirname(__FILE__) . '/view.inc.php');

view::head();
view::navbar($action);

// Active les check d'ajout d'un titre pour un POST
if (isset($playlistId)) {
    echo '<form action="index.php" method="POST">';
}

// Création du tableau
echo '  <table class="table table-hover">
                <thead>
                    <tr>
                        '.(isset($playlistId) ? '<th></th>' : '').'
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th class="text-center">Durée</th>
                        <th class="text-center">Genre</th>
                        <th class="text-right"></th>
                    </tr>
                </thead>
                <tbody>';

// Création de chaque élement du tableau
foreach ($tracks as $track) {
    echo '<tr class="cursorDefault">';
    if (isset($playlistId)) {
        echo '<td><input type="checkbox" name="playlistAddTracks[]" value="' . $track->getId() . '"></td>';
    }
    echo '<td>' . $track->getTitle() . '(' . $track->getId() . ')</td>
             <td>' . $track->getAuthor() . '</td>
             <td class="text-center">' . $track->getStripDuration() . '</td>
             <td class="text-center">' . $track->getGenre() . '</td>     
             <td class="text-right"><span title="Modifier" class="glyphicon glyphicon-edit cursor hover" data-toggle="modal" data-target="#trackModal"></span>&nbsp;<span title="Supprimer" class="glyphicon glyphicon-trash cursor hover" onclick="location.href=\'index.php?action=trackDelete&id=' . $track->getId() . '\'"></span></td>
        </tr>';
}

// Fermeture du tableau
echo '      </tbody>
        </table>';

// Boutton d'ajout des titres sélectionnés dans la playlist ouverte
if (isset($playlistId)) {
    echo '  <div class="text-center">
                <button type="submit" class="btn btn-lg btn-block btn-success">
                    <span class="glyphicon glyphicon-chevron-right"></span> Ajouter les titres sélectionnés à la playlist: 
                    <span class="bold">' . $playlist->getName() . '</span>
                </button>
            </div>
        </form>';
}

view::modals();
view::footer();