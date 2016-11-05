<!DOCTYPE html>

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
    <body> 
        <?php
        // Chargement de la configuration
        require('config.php');

        // Chargement des objets
        require('Playlist.php');
        require('Track.php');

        // Démarrage de la session utilisateur
        session_start();
		
		/*
		 * RECUPERATION DES VARIABLES GET/POST
		 */
 
        $fct = filter_input(INPUT_GET, 'fct', FILTER_SANITIZE_SPECIAL_CHARS);
        $playlistId = filter_input(INPUT_GET, 'playlistId', FILTER_SANITIZE_NUMBER_INT);

        $login = filter_input(INPUT_POST, 'login');
        $register = filter_input(INPUT_POST, 'register');
        $playlistAdd = filter_input(INPUT_POST, 'playlistAdd');
        $trackAdd = filter_input(INPUT_POST, 'trackAdd');

        $trackId = filter_input(INPUT_POST, 'trackId', FILTER_SANITIZE_NUMBER_INT);
        $trackTitle = filter_input(INPUT_POST, 'trackTitle', FILTER_SANITIZE_SPECIAL_CHARS);
        $trackAuthor = filter_input(INPUT_POST, 'trackAuthor', FILTER_SANITIZE_SPECIAL_CHARS);
        $trackDura = filter_input(INPUT_POST, 'trackDura', FILTER_SANITIZE_NUMBER_INT);

        $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_SPECIAL_CHARS);
        $email = filter_input(INPUT_POST, 'email', FILTER_SANITIZE_EMAIL);
        $password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_SPECIAL_CHARS);

        $playlistName = filter_input(INPUT_POST, 'playlistName', FILTER_SANITIZE_SPECIAL_CHARS);
        $playlistDesc = filter_input(INPUT_POST, 'playlistDesc', FILTER_SANITIZE_SPECIAL_CHARS);
        $playlistJaq = (isset($_FILES['playlistJaq']) ? $_FILES['playlistJaq'] : null);
        $playlistAddTrack = filter_input(INPUT_POST, 'playlistAddTrack');

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
        function refresh($loc = "") {
            //header('Location: index.php' . $loc);
            //die();
        }

        // Récupération de la session courante
        if (isLogin()) {
            $userid = (int) $_SESSION['userid'];
            $username = $_SESSION['username'];
        }

        /*
         * RECUPERATION DE LA PLAYLIST OUVERTE
         */
         
        if (isLogin() && ((isset($fct) && $fct == 'playlistShow' && isset($playlistId)) || isset($_SESSION['last_Playlist']))) {
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
        
		/*
		 * VERIFICATION DES DIVERS POST
		 */
        
		// Login d'un utilisateur
        if (isset($login)) {
            if ($username != null && $password != null) {

                // Récupération de l'utilisateur entré
                $select = bdd()->prepare('SELECT id, password FROM user WHERE username = :username');
                $select->bindValue(":username", $username);
                $select->execute();
                if (!$select) {
                    echo bdd()->errorInfo();
                    die();
                }
                
                // Vérification du mot de passe de l'utilisateur
                while ($data = $select->fetch()) {
                    if (password_verify($password, $data['password'])) {
                        $userid = $data['id'];
                        break;
                    } else {
                        header('Location: index.php?fct=badpwd');
                        die();
                    }
                }
                // Enregistrement de la session
                if (isset($userid)) {
                    $_SESSION['userid'] = (int) $userid;
                    $_SESSION['username'] = $username;
                }
            }
            refresh();
            
        // Ajout d'un utilisateur
        } elseif (isset($register)) {
			
            // Récupération de l'utilisateur entré
            $select = bdd()->prepare('SELECT id, password FROM user WHERE username = :username');
            $select->bindValue(":username", $username);
            $select->execute();
            if (!$select) {
                echo bdd()->errorInfo();
                die();
            }
            
            // Vérification si l'utilisateur existe déjà
            if ($data = $select->fetch()) {
                header('Location: index.php?fct=baduser');
                die();
            }

            // Création d'un nouvel utilisateur si il n'existait pas
            $insert = bdd()->prepare('INSERT INTO user VALUES(0, :username, :email, :password)');
            
            // Chiffrage du mot de passe
            pwHash = password_hash($password, PASSWORD_DEFAULT);

            $insert->bindValue(":username", $username);
            $insert->bindValue(":email", $email);
            $insert->bindValue(":password", $pwHash);
            $insert->execute();
			
            $userid = bdd()->lastInsertId();
            
            // Login de l'utilisateur enregistré
            if (isset($userid)) {
                $_SESSION['userid'] = (int) $userid;
                $_SESSION['username'] = $username;
            }
            refresh();
            
        // Blockage de tous les autres ajouts si il n'y a pas de login
		} elseif (isLogin()) {
			        
        // Ajout d'une playlist
        } elseif (isset($playlistAdd)) {
			
			// Création de la playlist
            $insert = bdd()->prepare('INSERT INTO `playlist-header` VALUES(0, :name, :desc, :jaq, :userid)');
            $insert->bindValue(":name", $playlistName);
            $insert->bindValue(":desc", $playlistDesc);
            $insert->bindValue(":userid", (int) $userid);
            if (!$select) {
                echo bdd()->errorInfo();
                die();
            }
            
            // Enregistrement d'une jaquette possible
            if (isset($playlistJaq)) {
				
				// Nom du fichier avec un nom unique
                $uploadFile = UPLOAD . uniqid('JAQ_') . '.' . pathinfo($playlistJaq['name'], PATHINFO_EXTENSION);
                
                // Vérification si le fichier existe
                if (file_exists($uploadFile)) {
                    refresh();
                }
                
                // Déplacement du fichier dans le bon dossier
                if (move_uploaded_file($playlistJaq['tmp_name'], $uploadFile)) {
                    $insert->bindValue(":jaq", $uploadFile);
                } else {
                    $insert->bindValue(":jaq", null);
                }
                
            } else {
                $insert->bindValue(":jaq", null);
            }
            
            $insert->execute();
            $playlistId = bdd()->lastInsertId();
            
            refresh('?fct=playlistShow&playlistID=' . $playlistId);
            
        // Ajout d'un titre
        } elseif (isset($trackAdd)) {
			
			// Création du titre
            $insert = bdd()->prepare('INSERT INTO `track` VALUES(0, :title, :author, :duration)');
            $insert->bindValue(":title", $trackTitle);
            $insert->bindValue(":author", $trackAuthor);
            $insert->bindValue(":duration", (int) $trackDura);
            $insert->execute();
			if (!$select) {
                echo bdd()->errorInfo();
                die();
            }
            
            refresh("?fct=tracks");
        
        // Ajout d'un titre dans une playlist
        } elseif (isset($playlistAddTrack)) {
            var_dump($playlistAddTrack);
            /*$insert = bdd()->prepare('INSERT INTO `playlist` VALUES(:playlistid, :trackid)');
            $insert->bindValue(":playlistid", (int) $playlistId);
            $insert->bindValue(":trackid", (int) $trackAuthor);
            $insert->execute();
            refresh("?fct=playlistShow");*/
            
		// Autres fonctions GET
        } elseif (isset($fct)) {
			
			// Logout d'un utilisateur
            if ($fct == 'logout') {
                session_destroy();
                refresh();
            }
        }
        
        ?>
        
        <?-- NAVBAR -->
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.php">Musik Player</a>
                </div>
                <ul class="nav navbar-nav">
                    <?php
                    
                    /*
                     * Gestion des menus
                     */
                     
                    if (isLogin()) {
						
						// Affichage de l'onglet playlist
						
						// Dane le cas où toutes les playlists (ou d'un utilisateur) doivent être affichées
                        echo '<li class="dropdown' . (!isset($fct) || ($fct != 'tracks' && $fct != 'playlistShow') ? ' active' : '') . '">';

						// Dans le cas où tous les titres ou les titres d'une playlist doivent être affichés
                        if (isset($fct) && ($fct == 'tracks' || $fct == 'playlistShow')) {
                            echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									<span class="glyphicon glyphicon-th-list"></span> Playlists<span class="caret"></span>
								</a>';
						
						// Dans le cas où toutes les playlists doivent être affiché
                        } elseif (isset($fct) && $fct == 'playlistAll') {
                            echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									<span class="glyphicon glyphicon-th-list"></span> Toutes les playlists<span class="caret"></span>
								</a>';
								
						// Dans le cas où les playlists d'un utilisateur doivent être affichées
                        } else {
                            echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									<span class="glyphicon glyphicon-list-alt"></span> Vos playlists<span class="caret"></span>
								</a>';
                        }
                        
                        // Ajout du menu déroulant des playlists
                        echo '<ul class="dropdown-menu">
                                    <li><a href="index.php"><span class="glyphicon glyphicon-list-alt"></span> Vos playlists</a></li>
                                    <li><a href="index.php?fct=playlistAll"><span class="glyphicon glyphicon-th-list"></span> Toutes les playlists</a></li>
                                    <li><a data-toggle="modal" href="#playlistModal"><span class="glyphicon glyphicon-plus"></span> Créer une playlist ...</a></li>
                                </ul>
                            </li>';
                            
                        
                        // Affichage de l'onglet playlist dans le cas où une playlist est ouverte
                        if ((isset($playlistId) && isset($playlist))) {
                            echo '<li class="dropdown' . (isset($fct) && $fct == 'playlistShow' ? ' active' : '') . '">';
                            
                            echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									<span class="glyphicon glyphicon-folder-open"></span> ' . $playlist->getName() . '<span class="caret"></span>
								</a>';
							
							// Ajout du menu déroulant de la playlist
                            echo '<ul class="dropdown-menu">
                                    <li><a href="index.php?fct=playlistShow"><span class="glyphicon glyphicon-list-alt"></span> Afficher la playlist</a></li>
                                    <li><a data-toggle="modal" href="#playlistModal"><span class="glyphicon glyphicon-edit"></span> Modifier la playlist ...</a></li>
                                    <li><a href="index.php?fct=tracks&playlistId=' . $playlistId . '"><span class="glyphicon glyphicon-plus"></span> Ajouter un titre ...</a></li>
                                </ul>
                            </li>';
                            //echo '<li class="active"><a data-toggle="modal" href="index.php?fct=playlistShow"><span class="glyphicon glyphicon-th-list"></span> '.$playlist->getName().'</a></li>';
                        }
                        
                        // Affichage de l'onglet des titres
                        echo '<li class="dropdown' . (isset($fct) && $fct == 'tracks' ? ' active' : '') . '">';

                        if (isset($fct) && $fct == 'tracks') {
                            echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									<span class="glyphicon glyphicon-music"></span> Tous les titres<span class="caret"></span>
								</a>';
                        } else {
                            echo '<a class="dropdown-toggle" data-toggle="dropdown" href="#">
									<span class="glyphicon glyphicon-music"></span> Titres<span class="caret"></span>
								</a>';
                        }
                        
						// Ajout du menu déroulant des titres
                        echo '<ul class="dropdown-menu">
                                    <li><a href="index.php?fct=tracks"><span class="glyphicon glyphicon-th-list"></span> Tous les titres</a></li>
                                    <li><a data-toggle="modal" href="#trackModal"><span class="glyphicon glyphicon-plus"></span> Ajouter un titre ...</a></li>
                                </ul>
                            </li>';
                    } else {
                        echo '<li ' . (!isset($fct) || $fct != 'tracks' ? 'class="active"' : '') . '><a href="index.php"><span class="glyphicon glyphicon-th-list"></span> Toutes les playlists</a></li>';
                        echo '<li ' . (isset($fct) && $fct == 'tracks' ? 'class="active"' : '') . '><a href="index.php?fct=tracks"><span class="glyphicon glyphicon-music"></span> Tous les titres</a></li>';
                    }
                    ?>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <?php
                    
                    // Affichage des options de configuration de l'utilisateur et de logout
                    if (isLogin()) {
                        echo '<li><a data-toggle="modal" href="index.php?fct=settings"><span class="glyphicon glyphicon-user"></span> ' . $username . ' (' . $userid . ')' . '</a></li>';
                        echo '<li><a data-toggle="modal" href="index.php?fct=logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>';
                        
                    // Affichage des options pour se login et s'enregistrer
                    } else {
                        echo '<li><a data-toggle="modal" href="#registerModal"><span class="glyphicon glyphicon-user"></span> S\'enregistrer</a></li>';
                        echo '<li><a data-toggle="modal" href="#loginModal"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>';
                    }
                    
                    ?>
                </ul>
            </div>
        </nav>
        <?php
        
        /*
         * GESTION DES ERREURS DE SAISIES
         */
        
        // Dans le cas où l'utilisateur s'est trompé de mot de passe
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
         * AUTRES DONNEES DIVERS
         */


		// Affichage de la jaquette et de la description d'une playlist
        if (isset($fct) && $fct == 'playlistShow') {
            echo '<div class="container text-center"><h2>';
            if ($playlist->getJaq()) {
                echo '<img height="100px" class="img-rounded" src="' . $playlist->getJaq() . '">&nbsp;';
            }
            echo $playlist->getDesc();
            echo '</h2></div>';
        }
        
        // Active les check d'ajout d'un titre pour un POST
        if (isset($fct) && $fct == 'tracks' && isset($playlistId)) {
            echo '<form action="index.php?fct=playlistShow" method="POST">';
        }
        
		/* 
		 * Création du tableau de musique
		 */
		 
        echo '<table class="table table-hover"><thead><tr>';
		
		// Création des libellé du tableau
		// Et recherche des données pour les cas de l'affichage d'un tableau de tous les titres, d'une playlist ou de toute les playlists
		
		// Libellé des titres ou d'une playlist
        if (isset($fct) && ($fct == 'tracks' || $fct == 'playlistShow')) {
            if (isset($playlistId)) {
                echo '<th></th>';
            }
            echo '<th>Titre</th>
                  <th>Auteur</th>
                  <th class="text-center">Durée</th>
                  <th class="text-right"></th>';
            if ($fct == 'tracks') {
				
				// Tous les titres
                $select = bdd()->prepare('SELECT * FROM `track` t');
            } else {
				
				// Tous les titres d'une playlist
                $select = bdd()->prepare('SELECT t.id, t.title, t.author, t.duration
                                            FROM `playlist` p
                                            INNER JOIN `track` t
                                            ON t.id = p.trackid
                                            WHERE p.playlistid = :playlistId');
                $select->bindValue(":playlistId", $playlistId);
            }
            
        // Libellé de toutes les playlist
        } elseif (isset($fct) && $fct == 'playlistAll' || !isLogin()) {
			
            echo '<th>Playlist</th>
                  <th>Description</th>
                  <th class="text-center">Utilisateur</th>
                  <th class="text-right">Nombre de titres</th>
                  <th class="text-right"></th>';
            
            // Toutes les playlists
            $select = bdd()->prepare('SELECT h.id, h.name, h.desc, h.jaqurl, u.username, GROUP_CONCAT(p.trackid)
                                        FROM `playlist-header` h
                                        INNER JOIN `user` u
                                        ON u.id = h.userid
                                        LEFT JOIN `playlist` p
                                        ON h.id = p.trackid
                                        GROUP BY h.id');
                                        
		// Libellé d'une playlist
        } else {
            echo '<th>Playlist</th>
                  <th>Description</th>
                  <th class="text-center">Titres</th>
                  <th class="text-right">Nombre de titres</th>
                  <th class="text-right"></th>';
            
            // Toutes les playlists d'un utilisateur
            $select = bdd()->prepare('SELECT h.id, h.name, h.desc, h.jaqurl, GROUP_CONCAT(p.trackid) 
                                        FROM `playlist-header` h 
                                        LEFT JOIN `playlist` p 
                                        ON h.id = p.trackid 
                                        WHERE h.userid = :userid 
                                        GROUP BY h.id');
            $select->bindValue(":userid", $userid);
        }
        echo '</tr></thead><tbody>';

        $select->execute();
        if (!$select) {
            die("Execute query error, because: " . bdd()->errorInfo());
        }
        
		// Création de chaque élement du tableau
        while ($data = $select->fetch()) {
			
			// Dans le cas de l'affichage de tous les titres ou d'une playlist
            if (isset($fct) && ($fct == 'tracks' || $fct == 'playlistShow')) {
                $track = new Track($data);
                echo '<tr class="cursorDefault">';
                if (isset($playlistId)) {
                    echo '<td><input type="checkbox" name="playlistAddTrack[]" value="' . $track->getId() . '"></td>';
                }
                echo '<td>' . $track->getTitle() . '(' . $track->getId() . ')</td>
                        <td>' . $track->getAuthor() . '</td>
                        <td class="text-center">' . $track->getStripDuration() . '</td>
                        <td class="text-right"><span title="Modifier" class="glyphicon glyphicon-edit cursor hover" data-toggle="modal" data-target="#trackModal"></span>&nbsp;<span title="Supprimer" class="glyphicon glyphicon-trash cursor hover" onclick="\'location.href=index.php?fct=trackDelete&id=' . $track->getId() . '\'"></span></td>
                    </tr>';
             
            // Dans le cas d'affichage de toutes les playlists
            } elseif (isset($fct) && $fct == 'playlistAll' || !isLogin()) {
                $playlist = new Playlist($data);
                echo '<tr class="cursor" ' . (isLogin() ? 'onclick="location.href=\'index.php?fct=playlistShow&playlistId=' . $playlist->getId() . '\'"' : 'data-toggle="modal" data-target="#loginModal"') . '>';
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
                    
            // Dans le cas d'affichage de toutes les playlists d'un utilisateur
            } else {
                $playlist = new Playlist($data);
                echo '<tr class="cursor" onclick="location.href=\'index.php?fct=playlistShow&playlistId=' . $playlist->getId() . '\'">';
                echo '<td>';
                if ($playlist->getJaq()) {
                    echo '<img height="25px" class="img-rounded" src="' . $playlist->getJaq() . '">&nbsp;';
                }
                echo $playlist->getName() . '(' . $playlist->getId() . ')' . '</td>';
                echo '<td>' . $playlist->getDesc() . '</td>
                      <td class="text-center">' . $playlist->getTracks() . '</td>
                      <td class="text-right">' . $playlist->getTracksCount() . '</td>
                      <td class="text-right"></td>
                    </tr>';
            }
        }

		// Fermeture du tableau
        echo '</tbody></table>';
        
        // Boutton d'ajout des titres sélectionnés dans la playlist ouverte
        if (isset($fct) && $fct == 'tracks' && isset($playlistId)) {
            echo '<div class="text-center"><button type="submit" class="btn btn-lg btn-block btn-success"><span class="glyphicon glyphicon-chevron-right"></span> Ajouter les titres sélectionnés à la playlist: <span class="bold">' . $playlist->getName() . '</span></button></div></form>';
        }

		/*
		 *  LES MODALS
		 */
		 
        if (isLogin()) {
            // Modal d'ajout d'une playlist
            echo '<div id="playlistModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-body">
                                <div class="modal-header" style="padding:35px 50px;">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="text-center"><span class="glyphicon glyphicon-th-list"></span> Ajouter une playlist</h4>
                                </div>
                                <div class="modal-body" style="padding:40px 50px;">
                                    <form role="form" name="playlistAdd" action="index.php" method="post" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label class="control-label" for="name"><span class="glyphicon glyphicon-music"></span> Nom de la playlist :</label>
                                            <input id="name" class="form-control" type="text" name="playlistName" placeholder="Nom de la playlist" required pattern=".{1,120}">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="desc"><span class="glyphicon glyphicon-edit"></span> Description :</label>
                                            <input id="desc" class="form-control" type="text" name="playlistDesc" placeholder="Description" required pattern=".{1,120}">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="playlistJaq"><span class="glyphicon glyphicon-file"></span> Jaquette :</label>
                                            <input type="file" name="playlistJaq" accept="image/*">
                                        </div>
                                        <button type="submit" name="playlistAdd" class="btn btn-success btn-block"><span class="glyphicon glyphicon-ok"></span> Ajouter la playlist</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>';

            // Modal d'ajout d'un titre
            echo '<div id="trackModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-body">
                                <div class="modal-header" style="padding:35px 50px;">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="text-center"><span class="glyphicon glyphicon-music"></span> Ajouter un titre</h4>
                                </div>
                                <div class="modal-body" style="padding:40px 50px;">
                                    <form role="form" name="trackAdd" action="index.php" method="post">
                                        <div class="form-group">
                                            <label class="control-label" for="name"><span class="glyphicon glyphicon-music"></span> Titre de la musique :</label>
                                            <input id="trackTitle" class="form-control" type="text" name="trackTitle" placeholder="Titre de la musique" required pattern=".{1,120}">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="desc"><span class="glyphicon glyphicon-user"></span> Auteur de la musique :</label>
                                            <input id="trackAuthor" class="form-control" type="text" name="trackAuthor" placeholder="Auteur de la musique" required pattern=".{1,120}">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="desc"><span class="glyphicon glyphicon-time"></span> Durée de la musique [s]:</label>
                                            <input id="trackDura" class="form-control" type="text" name="trackDura" placeholder="Durée de la musique en secondes" required pattern="[0-9]{1,120}">
                                        </div>
                                        
                                        <button type="submit" name="trackAdd" class="btn btn-success btn-block"><span class="glyphicon glyphicon-ok"></span> Ajouter le titre</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>';
        } else {

            // Modal pour enregistrer un utilisateur
            echo '<div id="registerModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-body">
                                <div class="modal-header" style="padding:35px 50px;">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="text-center"><span class="glyphicon glyphicon-lock"></span> S\'enregistrer</h4>
                                </div>
                                <div class="modal-body" style="padding:40px 50px;">
                                    <form role="form" name="register" action="index.php" method="post">
                                        <div class="form-group">
                                            <label class="control-label" for="email"><span class="glyphicon glyphicon-envelope"></span> Email :</label>
                                            <input id="email" class="form-control" type="email" name="email" placeholder="Email" required pattern=".{1,50}@.{1,50}">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="username"><span class="glyphicon glyphicon-user"></span> Utilisateur :</label>
                                            <input id="username" class="form-control" type="text" name="username" placeholder="Utilisateur" required pattern=".{1,120}">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="password"><span class="glyphicon glyphicon-eye-open"></span> Mot de passe :</label>
                                            <input id="password" class="form-control" type="password" name="password" placeholder="Mot de passe" required pattern=".{1,120}">
                                        </div>
                                        <button type="submit" name="register" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> S\'enregistrer</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>';

            // Modal pour login un utilisateur
            echo '<div id="loginModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-body">
                                <div class="modal-header" style="padding:35px 50px;">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="text-center"><span class="glyphicon glyphicon-lock"></span> Login</h4>
                                </div>
                                <div class="modal-body" style="padding:40px 50px;">
                                    <form role="form" name="login" action="index.php" method="post">
                                        <div class="form-group">
                                            <label class="control-label" for="username"><span class="glyphicon glyphicon-user"></span> Utilisateur :</label>
                                            <input id="username" class="form-control" type="text" name="username" placeholder="Utilisateur" required pattern=".{1,120}">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label" for="password"><span class="glyphicon glyphicon-eye-open"></span> Mot de passe :</label>
                                            <input id="password" class="form-control" type="password" name="password" placeholder="Mot de passe" required pattern=".{1,120}">
                                        </div>
                                        <button type="submit" name="login" class="btn btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
                                    </form>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>';
        }
        ?>
    </body>
</html>
