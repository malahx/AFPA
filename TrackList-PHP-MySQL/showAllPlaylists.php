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
    echo '<li class="dropdown active">';

    echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
            <span class="glyphicon glyphicon-th-list"></span> Toutes les playlists<span class="caret"></span>
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
} else {
    echo '<li class="active"><a href="showAllPlaylists.php"><span class="glyphicon glyphicon-th-list"></span> Toutes les playlists</a></li>';
    echo '<li><a href="showAllTracks.php"><span class="glyphicon glyphicon-music"></span> Tous les titres</a></li>';
}
echo '</ul>';

showNavBarUser();

echo '</div></nav>';

// Dans le cas où l'utilisateur s'est trompé de mot de passe

$fct = filter_input(INPUT_GET, 'fct', FILTER_SANITIZE_SPECIAL_CHARS);

if ($fct == 'badpwd') {
    echo '<div class="alert alert-warning fade in">'
    . '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
    . '<strong>Login!</strong> Mauvais mot de passe.</div>';

    // Dans le cas où l'utilisateur à voulu créer un utilisateur existant
} elseif ($fct == 'baduser') {
    echo '<div class="alert alert-warning fade in">'
    . '<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>'
    . '<strong>Enregistrement!</strong> Utilisateur existant.'
    . '</div>';
}

/*
 * CREATION DU TABLEAU
 */

echo '<table class="table table-hover"><thead><tr>';

// Création des libellé du tableau

echo '<th>Playlist</th>
         <th>Description</th>
         <th class="text-center">Utilisateur</th>
         <th class="text-right">Nombre de titres</th>
         <th class="text-right"></th>';

// Toutes les playlists MODIFICATION (Rémi, 20161109, 10:38) Correction de la requête mais l'affichage du nombre de titre n'est pas assuré
$select = bdd()->prepare('SELECT h.id, h.name, h.desc, h.jaqurl, u.username, 
                            FROM `playlist-header` h
                            INNER JOIN `user` u
                            ON u.id = h.userid 
                           
                            
                            ');


echo '</tr></thead><tbody>';


$select->execute();
if (!$select) {
    die("Execute query error, because: " . bdd()->errorInfo());
}

// Création de chaque élement du tableau
while ($data = $select->fetch()) {

    $playlist = new Playlist($data);
    
    // Importer une playlist ou ouvrir une playlist ou se logger
    if (isLogin()) {
        if ($playlist->getUser() == $_SESSION['username']) {
            $onclick = 'onclick="location.href=\'showPlaylistTracks.php?playlistId=' . $playlist->getId() . '\'"';
        } else {
            $onclick = 'onclick="location.href=\'importPlaylist.php?playlistId=' . $playlist->getId() . '\'"';
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
             <td class="text-center">' . $playlist->getUser() . '</td>
             <td class="text-right">' . $playlist->getTracksCount() . '</td>
             <td class="text-right"></td>
          </tr>';
}

// Fermeture du tableau
echo '</tbody></table>';

showModals();

echo '</body></html>';
