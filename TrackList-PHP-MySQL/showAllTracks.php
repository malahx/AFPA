<?php

// Chargement des fonctions divers
require('common.php');

echo '<!DOCTYPE html><html>';
head();
echo '<body>';

echo '<nav class = "navbar navbar-inverse">
        <div class = "container-fluid">
        <div class = "navbar-header">
            <a class = "navbar-brand" href = "index.php">Musik Player</a>
        </div>
        <ul class = "nav navbar-nav">';
/*
 * Gestion des menus
 */
if (isLogin()) {

// Affichage de l'onglet playlist
// Dane le cas où toutes les playlists (ou d'un utilisateur) doivent être affichées
    echo '<li class="dropdown">';

// Dans le cas où tous les titres ou les titres d'une playlist doivent être affichés
    echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <span class="glyphicon glyphicon-th-list"></span> Playlists<span class="caret"></span>
        </a>';

// Ajout du menu déroulant des playlists
    echo '<ul class="dropdown-menu">
            <li><a href="showUserPlaylists.php"><span class="glyphicon glyphicon-list-alt"></span> Vos playlists</a></li>
            <li><a href="showAllPlaylists.php"><span class="glyphicon glyphicon-th-list"></span> Toutes les playlists</a></li>
            <li><a data-toggle="modal" href="#playlistModal"><span class="glyphicon glyphicon-plus"></span> Créer une playlist ...</a></li>
        </ul>
    </li>';


// Affichage de l'onglet playlist dans le cas où une playlist est ouverte
    if ((isset($playlistId) && isset($playlist))) {
        echo '<li class="dropdown">';

        echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
                <span class="glyphicon glyphicon-folder-open"></span> ' . $playlist->getName() . '<span class="caret"></span>
            </a>';

        // Ajout du menu déroulant de la playlist
        echo '<ul class="dropdown-menu">
                <li><a href="showPlaylistTracks.php"><span class="glyphicon glyphicon-list-alt"></span> Afficher la playlist</a></li>
                <li><a data-toggle="modal" href="#playlistModal"><span class="glyphicon glyphicon-edit"></span> Modifier la playlist ...</a></li>
                <li><a href="showAllTracks.php?playlistId=' . $playlistId . '"><span class="glyphicon glyphicon-plus"></span> Ajouter un titre à la playlist ...</a></li>
            </ul>
        </li>';
    }

// Affichage de l'onglet des titres
    echo '<li class="dropdown active">';

    echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <span class="glyphicon glyphicon-music"></span> Tous les titres<span class="caret"></span>
        </a>';

// Ajout du menu déroulant des titres
    echo '<ul class="dropdown-menu">
            <li><a href="showAllTracks.php"><span class="glyphicon glyphicon-th-list"></span> Tous les titres</a></li>
            <li><a data-toggle="modal" href="#trackModal"><span class="glyphicon glyphicon-plus"></span> Ajouter un titre ...</a></li>
        </ul>
    </li>';
} else {
    echo '<li><a href="showAllPlaylists.php"><span class="glyphicon glyphicon-th-list"></span> Toutes les playlists</a></li>';
    echo '<li class="active"><a href="showAllTracks.php"><span class="glyphicon glyphicon-music"></span> Tous les titres</a></li>';
}
echo '</ul>';

showNavBarUser();

echo '</div></nav>';

// Active les check d'ajout d'un titre pour un POST
if (isset($playlistId)) {
    echo '<form action="addTrackToPlaylist.php" method="POST">';
}

/*
 * CREATION DU TABLEAU
 */

echo '<table class="table table-hover"><thead><tr>';

// Création des libellé du tableau
if (isset($playlistId)) {
    echo '<th></th>';
}
echo    '<th>Titre</th>
         <th>Auteur</th>
         <th class="text-center">Durée</th>
         <th class="text-right"></th>';

// Tous les titres
$select = bdd()->prepare('SELECT * FROM `track` t');

echo '</tr></thead><tbody>';

$select->execute();
if (!$select) {
    die("Execute query error, because: " . bdd()->errorInfo());
}

// Création de chaque élement du tableau
while ($data = $select->fetch()) {

    // Dans le cas de l'affichage de tous les titres ou d'une playlist
    $track = new Track($data);
    echo '<tr class="cursorDefault">';
    if (isset($playlistId)) {
        echo '<td><input type="checkbox" name="addTrackToPlaylist[]" value="' . $track->getId() . '"></td>';
    }
    echo    '<td>' . $track->getTitle() . '(' . $track->getId() . ')</td>
             <td>' . $track->getAuthor() . '</td>
             <td class="text-center">' . $track->getStripDuration() . '</td>
             <td class="text-right"><span title="Modifier" class="glyphicon glyphicon-edit cursor hover" data-toggle="modal" data-target="#trackModal"></span>&nbsp;<span title="Supprimer" class="glyphicon glyphicon-trash cursor hover" onclick="location.href=\'remTrack.php?id=' . $track->getId() . '\'"></span></td>
        </tr>';

    // Dans le cas d'affichage de toutes les playlists
}

// Fermeture du tableau
echo '</tbody></table>';

// Boutton d'ajout des titres sélectionnés dans la playlist ouverte
if (isset($playlistId)) {
    echo '<div class="text-center"><button type="submit" class="btn btn-lg btn-block btn-success"><span class="glyphicon glyphicon-chevron-right"></span> Ajouter les titres sélectionnés à la playlist: <span class="bold">' . $playlist->getName() . '</span></button></div></form>';
}

showModals();

echo '</body></html>';
