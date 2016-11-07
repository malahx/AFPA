<?php

// Chargement des fonctions divers
require('common.php');

// Redirection pour un utilisateur non login
if (!isLogin()) {
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
echo '<li class="dropdown active">';


echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
        <span class="glyphicon glyphicon-list-alt"></span> Vos playlists<span class="caret"></span>
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

/*
 * CREATION DU TABLEAU
 */

echo '<table class="table table-hover"><thead><tr>';

// Création des libellé du tableau

echo '<th>Playlist</th>
                  <th>Description</th>
                  <th class="text-center">Titres</th>
                  <th class="text-right">Nombre de titres</th>
                  <th class="text-right"></th>
                  <th class="text-right"></th>';

// Toutes les playlists d'un utilisateur
$select = bdd()->prepare('SELECT h.id, h.name, h.desc, h.jaqurl, GROUP_CONCAT(p.trackid) 
                            FROM `playlist-header` h 
                            LEFT JOIN `playlist` p 
                            ON h.id = p.trackid 
                            WHERE h.userid = :userid 
                            GROUP BY h.id');
$select->bindValue(":userid", $userid);

echo '</tr></thead><tbody>';

$select->execute();
if (!$select) {
    die("Execute query error, because: " . bdd()->errorInfo());
}

// Création de chaque élement du tableau
while ($data = $select->fetch()) {

// Dans le cas d'affichage de toutes les playlists d'un utilisateur
    $playlist = new Playlist($data);
    $onclick = 'onclick="location.href=\'showPlaylistTracks.php?playlistId=' . $playlist->getId() . '\'"';
    echo '<tr class="cursor">';
    echo '<td '.$onclick.'>';
    if ($playlist->getJaq()) {
        echo '<img height="25px" class="img-rounded" src="' . $playlist->getJaq() . '">&nbsp;';
    }
    echo $playlist->getName() . '(' . $playlist->getId() . ')' . '</td>';
    echo    '<td '.$onclick.'>' . $playlist->getDesc() . '</td>
             <td '.$onclick.' class="text-center">' . $playlist->getTracks() . '</td>
             <td '.$onclick.' class="text-right">' . $playlist->getTracksCount() . '</td>
             <td '.$onclick.' class="text-right"></td>
             <td class="text-right"><span title="Modifier" class="glyphicon glyphicon-edit cursor hover" data-toggle="modal" data-target="#playlistModal"></span>&nbsp;<span title="Supprimer" class="glyphicon glyphicon-trash cursor hover" onclick="location.href=\'remPlaylist.php?id=' . $playlist->getId() . '\'"></span></td>
          </tr>';
}

// Fermeture du tableau
echo '</tbody></table>';

showModals();

echo '</body></html>';
