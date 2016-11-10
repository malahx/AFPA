<?php

require_once dirname(__FILE__) . '/Modal/Modal.class.php';

class view {

    static function head() {
        echo '  <!DOCTYPE html>
                    <html>
                        <head>
                            <meta charset="UTF-8">
                            <title>Musik Player</title>
                            <link rel="stylesheet" type="text/css" href="index.css" />
                            <meta name="viewport" content="width=device-width, initial-scale=1">
                            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
                            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
                            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
                        </head>
                        <body>';
    }

    static function navbar($current) {
        echo '          <nav class = "navbar navbar-inverse">
                                <div class = "container-fluid">
                                    <div class = "navbar-header">
                                        <a class = "navbar-brand" href = "index.php">Musik Player</a>
                                    </div>
                                    <ul class = "nav navbar-nav">';

        self::Playlists($current == 'playlists' || $current == 'myPlaylists', $current);
        self::Authors($current == 'authors');
        self::Genres($current == 'genres');
        self::Tracks($current == 'tracks');

        echo '                      </ul>';

        self::Users();

        echo '                  </div>
                            </nav>';
    }

    private static function Playlists($active, $current) {
        if (!isLogin()) {
            echo '  <li class="' . ($active ? 'active' : '') . '"><a href="index.php?action=playlists"><span class="glyphicon glyphicon-th-list"></span> Toutes les playlists</a></li>';
        } else {
            echo '  <li class="dropdown ' . ($active ? 'active' : '') . '">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="glyphicon glyphicon-th-list"></span> ' . ($active ? ($current == 'playlists' ? 'Toutes les playlists' : 'Mes playlists') : 'Playlists') . '<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="index.php?action=myplaylists"><span class="glyphicon glyphicon-list-alt"></span> Mes playlists</a></li>
                            <li><a href="index.php?action=playlists"><span class="glyphicon glyphicon-th-list"></span> Toutes les playlists</a></li>
                            <li><a data-toggle="modal" href="#playlistModal"><span class="glyphicon glyphicon-plus"></span> Créer une playlist ...</a></li>
                        </ul>
                    </li>';
        }
    }

    private static function Genres($active) {
        if (isLogin()) {
            echo '  <li class="dropdown ' . ($active ? 'active' : '') . '">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="glyphicon glyphicon-flash"></span> Genres<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="index.php?action=genres"><span class="glyphicon glyphicon-flash"></span> Genres enregistrés</a></li>
                            <li><a data-toggle="modal" href="#genreModal"><span class="glyphicon glyphicon-plus"></span> Ajouter un genre ...</a></li>
                        </ul>
                    </li>';
        }
    }

    private static function Authors($active) {
        if (isLogin()) {
            echo '  <li class="dropdown ' . ($active ? 'active' : '') . '">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="glyphicon glyphicon-users"></span> Auteurs<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="index.php?action=autheurs"><span class="glyphicon glyphicon-users"></span> Auteurs enregistrés</a></li>
                            <li><a data-toggle="modal" href="#genreModal"><span class="glyphicon glyphicon-plus"></span> Ajouter un auteur ...</a></li>
                        </ul>
                    </li>';
        }
    }

    private static function Tracks($active) {
        if (!isLogin()) {
            echo '  <li class="' . ($active ? 'active' : '') . '"><a href="index.php?action=tracks"><span class="glyphicon glyphicon-music"></span> Tous les titres</a></li>';
        } else {
            echo '  <li class="dropdown ' . ($active ? 'active' : '') . '">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="glyphicon glyphicon-music"></span> ' . ($active ? 'Tous les titres' : 'Titres') . '<span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="showAllTracks.php"><span class="glyphicon glyphicon-th-list"></span> Tous les titres</a></li>
                            <li><a data-toggle="modal" href="#trackModal"><span class="glyphicon glyphicon-plus"></span> Ajouter un titre ...</a></li>
                        </ul>
                    </li>';
        }
    }

    private static function Users() {
        global $username, $userid;
        echo '  <ul class="nav navbar-nav navbar-right">';
        if (isLogin()) {
            echo '  <li><a data-toggle="modal" href="userSettings.php"><span class="glyphicon glyphicon-user"></span> ' . $username . ' (' . $userid . ')' . '</a></li>';
            echo '  <li><a href="userLogout.php"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>';
        } else {
            echo '  <li><a data-toggle="modal" href="#registerModal"><span class="glyphicon glyphicon-user"></span> S\'enregistrer</a></li>';
            echo '  <li><a data-toggle="modal" href="#loginModal"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>';
        }
        echo '  </ul>';
    }

    static function modals() {
        if (!isLogin()) {
            self::modal(self::modalLogin());
            self::modal(self::modalRegister());
        } else {
            self::modal(self::modalplaylistAdd());
            self::modal(self::modaltrackAdd());
            self::modal(self::modalauthorAdd());
            self::modal(self::modalgenreAdd());
        }
    }

    private static function modalLogin() {
        $inputs = array(
            new Input('text', 'username', 'Utilisateur', 'user'),
            new Input('password', 'password', 'Mot de passe', 'eye-open'),
        );
        $modal = new Modal('loginModal', 'Login', 'off', 'index.php', $inputs);
        return $modal;
    }

    private static function modalRegister() {
        $inputs = array(
            new Input('email', 'email', 'Email', 'envelope'),
            new Input('text', 'username', 'Utilisateur', 'user'),
            new Input('password', 'password', 'Mot de passe', 'eye-open'),
        );
        $modal = new Modal('registerModal', 'S\'enregistrer', 'off', 'index.php', $inputs);
        return $modal;
    }

    private static function modal($modal) {
        require dirname(__FILE__) . '/Modal/ModalTemplate.php';
    }

    static function footer() {
        echo '      </body>
                </html>';
    }

}
