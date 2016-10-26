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
            <p>Vous écoutez : <span>Titre</span> - <span>Auteur</span> (<span>Année</span>) - <span>Durée</span> <span class="glyphicon glyphicon-stop"></p>
        </div>
        <div class="container">
            <a type="button" class="btn btn-success btn-lg btn-block" href="index.php">Ajouter un titre</a>

            <?php
                require ("track.php");
                session_start();

                if (isset($_POST['clear'])) {
                    session_destroy();
                }

                if (isset($_POST['title']) && isset($_POST['author']) && isset($_POST['year']) && isset($_POST['length'])) {

                    if (!isset($_SESSION['id'])) {
                        $_SESSION['id'] = -1;
                    }

                    if (!isset($_SESSION['tracks'])) {
                        $_SESSION['tracks'] = Array();
                    }

                    $_SESSION['id']++;

                    $title =    htmlspecialchars($_POST['title']);
                    $author =   htmlspecialchars($_POST['author']);
                    $year =     (int) htmlspecialchars($_POST['year']);
                    $length =   (int) htmlspecialchars($_POST['length']);

                    $track = new Track($_SESSION['id'], $title, $author, $year, $length);

                    array_push($_SESSION['tracks'], $track);
                    
                    header('Location: tracklist.php');
                }
                if (isset($_SESSION['tracks'])) {
                    echo '<table class="table table-hover"><thead>
                            <tr>
                              <th>Titre</th>
                              <th>Auteur</th>
                              <th class="text-center">Année</th>
                              <th class="text-center">Durée</th>
                              <th class="text-right"></th>
                            </tr>
                          </thead><tbody>';
                    foreach ($_SESSION['tracks'] as $track) {
                        $minutes = floor($track->getLength()/ 60);
                        $seconds = $track->getLength() - $minutes * 60;
                        if (strlen($seconds) === 0) {
                            $seconds = '00';
                        } else if (strlen($seconds) === 1) {
                            $seconds = '0'.$seconds;
                        }
                        echo '<tr id ="track'.$track->getId().'">';
                        echo '<td>'.$track->getTitle().'</td>';
                        echo '<td>'.$track->getAuthor().'</td>';
                        echo '<td class="text-center">'.$track->getYear().'</td>';
                        echo '<td class="text-center">'.$minutes.':'.$seconds.'</td>';
                        echo '<td class="text-right"><span class="glyphicon glyphicon-play"></span><span class="glyphicon glyphicon-trash"></span></td>';
                        echo '</tr>';
                    }
                    echo '</tbody></table>';
                }
            ?>
        </div>
    </body>
</html>
