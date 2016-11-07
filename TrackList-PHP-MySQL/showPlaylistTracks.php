<?php

// Chargement des fonctions divers
require('common.php');

// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
    die();
}

if ((!isset($playlistId) || !isset($playlist))) {
    refresh();
    die();
}

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
    echo '<li class="dropdown active">';

    echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <span class="glyphicon glyphicon-folder-open"></span> ' . $playlist->getName() . '<span class="caret"></span>
        </a>';

    // Ajout du menu déroulant de la playlist
    echo '<ul class="dropdown-menu">
            <li><a href="showUserPlaylists.php"><span class="glyphicon glyphicon-list-alt"></span> Afficher la playlist</a></li>
            <li><a data-toggle="modal" href="#playlistModal"><span class="glyphicon glyphicon-edit"></span> Modifier la playlist ...</a></li>
            <li><a href="showAllTracks.php?playlistId=' . $playlistId . '"><span class="glyphicon glyphicon-plus"></span> Ajouter un titre à la playlist ...</a></li>
        </ul>
    </li>';

// Affichage de l'onglet des titres
echo '<li class="dropdown">';
echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
        <span class="glyphicon glyphicon-music"></span> Titres<span class="caret"></span>
    </a>';

// Ajout du menu déroulant des titres
echo '<ul class="dropdown-menu">
        <li><a href="showAllTracks.php"><span class="glyphicon glyphicon-th-list"></span> Tous les titres</a></li>
        <li><a data-toggle="modal" href="#trackModal"><span class="glyphicon glyphicon-plus"></span> Ajouter un titre ...</a></li>
    </ul>
</li>';
echo '</ul>';

showNavBarUser();

echo '</div></nav>';

// Affichage de la jaquette et de la description d'une playlist
echo '<div class="container text-center"><h2>';
if ($playlist->getJaq()) {
    echo '<img height="100px" class="img-rounded" src="' . $playlist->getJaq() . '">&nbsp;';
}
echo $playlist->getDesc();
echo '</h2></div>';

// Active les check d'ajout d'un titre pour un POST
if (isset($playlistId)) {
    echo '<form action="remTrackFromPlaylist.php" method="POST">';
}

/*
 * Création du tableau de musique
 */

echo '<table class="table table-hover"><thead><tr>';

// Création des libellé du tableau
// Et recherche des données pour les cas de l'affichage d'un tableau de tous les titres, d'une playlist ou de toute les playlists
// Libellé des titres ou d'une playlist
if (isset($playlistId)) {
    echo '<th></th>';
}
echo    '<th>Titre</th>
         <th>Auteur</th>
         <th class="text-center">Durée</th>
         <th class="text-right"></th>';

// Tous les titres d'une playlist
$select = bdd()->prepare('SELECT t.id, t.title, t.author, t.duration
                            FROM `playlist` p
                            INNER JOIN `track` t
                            ON t.id = p.trackid
                            WHERE p.playlistid = :playlistId');
$select->bindValue(":playlistId", $playlistId);

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
        echo '<td><input type="checkbox" name="remTrackFromPlaylist[]" value="' . $track->getId() . '"></td>';
    }
    echo    '<td>' . $track->getTitle() . '(' . $track->getId() . ')</td>
             <td>' . $track->getAuthor() . '</td>
             <td class="text-center">' . $track->getStripDuration() . '</td>
             <td class="text-right"><span title="Modifier" class="glyphicon glyphicon-edit cursor hover" data-toggle="modal" data-target="#trackModal"></span>&nbsp;<span title="Supprimer" class="glyphicon glyphicon-trash cursor hover" onclick="location.href=\'remTrackFromPlaylist.php?id=' . $track->getId() . '\'"></span></td>
        </tr>';
}

// Fermeture du tableau
echo '</tbody></table>';

// Boutton d'ajout des titres sélectionnés dans la playlist ouverte
if (isset($playlistId)) {
    echo '<div class="text-center"><button type="submit" class="btn btn-lg btn-block btn-danger"><span class="glyphicon glyphicon-chevron-left"></span> supprimé les titres sélectionnés de la playlist</button></div></form>';
}

showModals();

echo '</body></html>';
