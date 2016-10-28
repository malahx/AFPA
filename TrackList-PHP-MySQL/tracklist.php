<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Musik Player</title>
        <link rel="stylesheet" type="text/css" href="index.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    </head>
    <body>
        <div class="jumbotron text-center">
            <h1>Music Player</h1>
            <?php
            
            // Chargement de l'objet Track
            require ("track.php");
            require ("config.php");
            
            // Démarrage de la session
            session_start();
            
            // Connexion à la base de donnée
            $bdd = new PDO(BDD_DSN, BDD_USERNAME, BDD_PASSWORD);
            if (!$bdd) {
                die('Connexion impossible : ' . mysql_error());
            }
            // Récupération de toutes les variables POST
            $id =       filter_input(INPUT_POST, 'id',      FILTER_SANITIZE_NUMBER_INT);
            $title =    filter_input(INPUT_POST, 'title',   FILTER_SANITIZE_SPECIAL_CHARS);
            $author =   filter_input(INPUT_POST, 'author',  FILTER_SANITIZE_SPECIAL_CHARS);
            $year =     filter_input(INPUT_POST, 'year',    FILTER_SANITIZE_NUMBER_INT);
            $length =   filter_input(INPUT_POST, 'length',  FILTER_SANITIZE_NUMBER_INT);

            $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_SPECIAL_CHARS);
            $password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_SPECIAL_CHARS);
            
            
            if (isset($_SESSION['userid']) && isset($_SESSION['username'])) {
                // Récupération de la session courante
                $userid = $_SESSION['userid'];
                $username = $_SESSION['username'];
                echo '<p>Bienvenue '.$_SESSION['username'].'('.$_SESSION['userid'].')</p>';
            } else if ($username != null && $password != null) {
                
                // Récupération de l'utilisateur entré
                //$query = $bdd->query('SELECT userid FROM tracks WHERE username = \''.$username.'\';');
                $query = $bdd->prepare('SELECT userid, password FROM users WHERE username = :username');
                $query->bindValue(":username", $username);
                $query->execute();
                if(!$query) {
                    echo $bdd->errorInfo();
                }
                
                // Vérification du mot de passe de l'utilisateur
                while ($data = $query->fetch()) {
                    if (password_verify($password, $data['password'])) {
                        $userid = $data['userid'];
                    } else {
                        header('Location: index.php');
                        die("Mauvais mot de passe ...");
                     }
                }
                
                if (!isset($userid)) {
                    // Création d'un nouvel utilisateur si il n'existait pas
                    $pwHash = password_hash($password, PASSWORD_DEFAULT);
                    $query = $bdd->prepare('INSERT INTO users VALUES(0, :username, :password)');
                    $query->bindValue(":username", $username);
                    $query->bindValue(":password", $pwHash);
                    $query->execute();
                    //$bdd->exec('INSERT INTO users(\'username\', \'password\') VALUES(\''.$username.'\', \''.$pwHash.'\');');
                    $userid = $bdd->lastInsertId();
                }
                // Enregistrement de la session
                $_SESSION['userid'] = $userid;
                $_SESSION['username'] = $username;
            } else {
                // Personne ne s'est login
                header('Location: index.php');
            }
            ?>
            <p>Vous écoutez : <span>Titre</span> - <span>Auteur</span> (<span>Année</span>) - <span>Durée</span> <span class="glyphicon glyphicon-stop"></p>
        </div>
        <div class="container">
            <a type="button" class="btn btn-success btn-lg btn-block" href="index.php">Ajouter un titre</a>

            <?php
            // Vérification que l'utilisateur à ajouter une musique
            if ($title != null && $author != null && $year != null && $length != null && $userid != null) {                 
                    if ($id == null) {
                        //$bdd->exec('INSERT INTO tracks VALUES(0, \''.$title.'\', \''.$author.'\', '.$year.', '.$length.', '.$userid.');');

                        // Insertion de la nouvelle musique
                        $query = $bdd->prepare('INSERT INTO tracks VALUES(0, :title, :author, :year, :length, :userid)');
                        $query->bindValue(":title",     $title);
                        $query->bindValue(":author",    $author);
                        $query->bindValue(":year",      (int)$year);
                        $query->bindValue(":length",    (int)$length);
                        $query->bindValue(":userid",    (int)$userid);
                        $query->execute();
                    } else {
                        // Mise à jour d'un musique
                        $query = $bdd->prepare('UPDATE tracks SET title = :title, author = :author, year = :year, length = :length WHERE userid = :userid AND id = :id');
                        $query->bindValue(":id",        (int)$id);
                        $query->bindValue(":title",     $title);
                        $query->bindValue(":author",    $author);
                        $query->bindValue(":year",      (int)$year);
                        $query->bindValue(":length",    (int)$length);
                        $query->bindValue(":userid",    (int)$userid);
                        $query->execute();
                    }
                    // Eviter de reposter un POST
                    header('Location: tracklist.php');
                    die();
                }                
                
                // Création du tableau de musique
                echo '<table class="table table-hover"><thead>
                        <tr>
                          <th>Titre</th>
                          <th>Auteur</th>
                          <th class="text-center">Année</th>
                          <th class="text-center">Durée</th>
                          <th class="text-right"></th>
                        </tr>
                      </thead><tbody>';
                //$query = $bdd->query('SELECT * FROM tracks WHERE userid = \''.$userid.'\';');
                
                // Récupération de toutes les musiques de l'utilisateur
                $query = $bdd->prepare('SELECT * FROM tracks WHERE userid = :userid');
                $query->bindValue(":userid", (int)$userid);
                $query->execute();
                if(!$query) {
                    die("Execute query error, because: ". $bdd->errorInfo());
                }
                
                // Création de chaque élement du tableau
                while ($data = $query->fetch()) {
                    $track = new Track($data);
                    echo '<tr id="track'.$track->getId().'">';
                    echo '<td>'.$track->getTitle().'('.$track->getId().')'.'</td>';
                    echo '<td>'.$track->getAuthor().'</td>';
                    echo '<td class="text-center">'.$track->getYear().'</td>';
                    echo '<td class="text-center">'.$track->getDuration().'</td>';
                    echo '<td class="text-right">'
                        . '<a href="#" class="btnStart glyphicon glyphicon-play"></a>'
                        . '<a href="update.php?id='.$track->getId().'&title='.$track->getTitle().'&author='.$track->getAuthor().'&year='.$track->getYear().'&length='.$track->getLength().'" class="btnStart glyphicon glyphicon-edit"></a>'
                        . '<a href="delete.php?id='.$track->getId().'" class="btnDel glyphicon glyphicon-trash"></a>'
                      . '</td>';
                    echo '</tr>';
                }
                
                // Fermeture du tableau
                echo '</tbody></table>';
            ?>
        </div>
    </body>
</html>
