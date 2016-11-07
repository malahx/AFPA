<?php

// Chargement de la configuration
require('config.inc.php');

// Chargement des objets
require('obj/Playlist.php');
require('obj/Track.php');

// Démarrage de la session utilisateur
session_start();

/*
 * CONNEXION UNIQUE A LA BASE DE DONNEE AU MOMENT OPPORTUN
 */

$_bdd = null;

function bdd() {
    global $_bdd;
    if (!isset($_bdd)) {
        $_bdd = new PDO(BDD_DSN, BDD_USERNAME, BDD_PASSWORD);
    }
    return $_bdd;
}

// Fonction vérifiant si l'utilisateur est login
function isLogin() {
    return isset($_SESSION['userid']) && isset($_SESSION['username']);
}

// Fonction permettant de raffraichir la page
function refresh($loc = null) {
    header('Location: ' . ($loc != null ? $loc : 'showAllPlaylists.php'));
    die();
}

function head() {
    echo '<head>
        <meta charset="UTF-8">
        <title>Musik Player</title>
        <link rel="stylesheet" type="text/css" href="index.css" />
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>';
}

function showNavBarUser() {
    global $username, $userid;

    echo '<ul class="nav navbar-nav navbar-right">';
    // Affichage des options de configuration de l'utilisateur et de logout
    if (isLogin()) {
        echo '<li><a data-toggle="modal" href="user_settings.php"><span class="glyphicon glyphicon-user"></span> ' . $username . ' (' . $userid . ')' . '</a></li>';
        echo '<li><a href="user_logout.php"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>';

        // Affichage des options pour se login et s'enregistrer
    } else {
        echo '<li><a data-toggle="modal" href="#registerModal"><span class="glyphicon glyphicon-user"></span> S\'enregistrer</a></li>';
        echo '<li><a data-toggle="modal" href="#loginModal"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>';
    }
    echo '</ul>';
}

function modalLogin() {
    echo '<div id="loginModal" class="modal fade" role="dialog"><div class="modal-dialog">
            <!-- Modal content--><div class="modal-content">
                <div class="modal-body"><div class="modal-header" style="padding:35px 50px;">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="text-center"><span class="glyphicon glyphicon-lock"></span> Login</h4>
                        </div><div class="modal-body" style="padding:40px 50px;">
                            <form role="form" name="login" action="user_login.php" method="post">
                                <div class="form-group"><label class="control-label" for="username"><span class="glyphicon glyphicon-user"></span> Utilisateur :</label>
                                            <input id="username" class="form-control" type="text" name="username" placeholder="Utilisateur" required pattern=".{1,120}">
                                        </div><div class="form-group"><label class="control-label" for="password"><span class="glyphicon glyphicon-eye-open"></span> Mot de passe :</label>
                                            <input id="password" class="form-control" type="password" name="password" placeholder="Mot de passe" required pattern=".{1,120}">
                                        </div>
                                        <button type="submit" name="login" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Cancel</button>
                                </div></div></div></div></div>';
}

function modalRegister() {
    echo '<div id="registerModal" class="modal fade" role="dialog"><div class="modal-dialog">
            <!-- Modal content--><div class="modal-content">
                        <div class="modal-body"><div class="modal-header" style="padding:35px 50px;">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="text-center"><span class="glyphicon glyphicon-lock"></span> S\'enregistrer</h4>
                                </div><div class="modal-body" style="padding:40px 50px;">
                                    <form role="form" name="register" action="user_register.php" method="post">
                                        <div class="form-group"><label class="control-label" for="email"><span class="glyphicon glyphicon-envelope"></span> Email :</label>
                                            <input id="email" class="form-control" type="email" name="email" placeholder="Email" required pattern=".{1,50}@.{1,50}">
                                        </div><div class="form-group"><label class="control-label" for="username"><span class="glyphicon glyphicon-user"></span> Utilisateur :</label>
                                            <input id="username" class="form-control" type="text" name="username" placeholder="Utilisateur" required pattern=".{1,120}">
                                        </div><div class="form-group"><label class="control-label" for="password"><span class="glyphicon glyphicon-eye-open"></span> Mot de passe :</label>
                                            <input id="password" class="form-control" type="password" name="password" placeholder="Mot de passe" required pattern=".{1,120}">
                                        </div>
                                        <button type="submit" name="register" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> S\'enregistrer</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Cancel</button>
                                </div></div></div></div></div>';
}

function modalAddTrack() {
    echo '<div id="trackModal" class="modal fade" role="dialog"><div class="modal-dialog">
                        <!-- Modal content--><div class="modal-content">
                            <div class="modal-body"><div class="modal-header" style="padding:35px 50px;">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="text-center"><span class="glyphicon glyphicon-music"></span> Ajouter un titre</h4>
                                </div><div class="modal-body" style="padding:40px 50px;">
                                    <form role="form" name="trackAdd" action="addTrack.php" method="post">
                                        <div class="form-group"><label class="control-label" for="name"><span class="glyphicon glyphicon-music"></span> Titre de la musique :</label>
                                            <input id="trackTitle" class="form-control" type="text" name="trackTitle" placeholder="Titre de la musique" required pattern=".{1,120}">
                                        </div><div class="form-group"><label class="control-label" for="desc"><span class="glyphicon glyphicon-user"></span> Auteur de la musique :</label>
                                            <input id="trackAuthor" class="form-control" type="text" name="trackAuthor" placeholder="Auteur de la musique" required pattern=".{1,120}">
                                        </div><div class="form-group"><label class="control-label" for="desc"><span class="glyphicon glyphicon-time"></span> Durée de la musique [s]:</label>
                                            <input id="trackDura" class="form-control" type="text" name="trackDura" placeholder="Durée de la musique en secondes" required pattern="[0-9]{1,120}">
                                        </div>
                                        <button type="submit" name="trackAdd" class="btn btn-success btn-block"><span class="glyphicon glyphicon-ok"></span> Ajouter le titre</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Cancel</button>
                                </div></div></div></div></div>';
}

function modalAddPlaylist() {
    echo '<div id="playlistModal" class="modal fade" role="dialog"><div class="modal-dialog">
                        <!-- Modal content--><div class="modal-content">
                            <div class="modal-body"><div class="modal-header" style="padding:35px 50px;">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="text-center"><span class="glyphicon glyphicon-th-list"></span> Ajouter une playlist</h4>
                                </div><div class="modal-body" style="padding:40px 50px;">
                                    <form role="form" name="playlistAdd" action="addPlaylist.php" method="post" enctype="multipart/form-data">
                                        <div class="form-group"><label class="control-label" for="name"><span class="glyphicon glyphicon-music"></span> Nom de la playlist :</label>
                                            <input id="name" class="form-control" type="text" name="playlistName" placeholder="Nom de la playlist" required pattern=".{1,120}">
                                        </div><div class="form-group"><label class="control-label" for="desc"><span class="glyphicon glyphicon-edit"></span> Description :</label>
                                            <input id="desc" class="form-control" type="text" name="playlistDesc" placeholder="Description" required pattern=".{1,120}">
                                        </div><div class="form-group"><label class="control-label" for="playlistJaq"><span class="glyphicon glyphicon-file"></span> Jaquette :</label>
                                            <input type="file" name="playlistJaq" accept="image/*">
                                        </div>
                                        <button type="submit" name="playlistAdd" class="btn btn-success btn-block"><span class="glyphicon glyphicon-ok"></span> Ajouter la playlist</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Cancel</button>
                                </div></div></div></div></div>';
}

function showModals() {
    if (isLogin()) {
        modalAddPlaylist();
        modalAddTrack();
    } else {
        modalLogin();
        modalRegister();
    }
}

// Récupération de la session courante
if (isLogin()) {
    $userid = (int) $_SESSION['userid'];
    $username = $_SESSION['username'];

    // Récupération de la playlist ouverte

    $playlistId = filter_input(INPUT_GET, 'playlistId', FILTER_SANITIZE_NUMBER_INT);

    if (isset($playlistId) || isset($_SESSION['last_Playlist'])) {
        if (isset($playlistId)) {
            $_SESSION['last_Playlist'] = $playlistId;
        } else {
            $playlistId = $_SESSION['last_Playlist'];
        }
        $select = bdd()->prepare('SELECT * FROM `playlist-header` WHERE id = :id');
        $select->bindValue(":id", $playlistId);
        $select->execute();
        $data = $select->fetch();
        if (!$data) {
            unset($_SESSION['last_Playlist']);
            refresh();
        }
        $playlist = new Playlist($data);
    }
}