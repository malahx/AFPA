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
        <meta content="width=device-width,initial-scale=1" name=viewport>
    </head>
    <body>
        <div class="jumbotron text-center">
            <h1>Ajouter une musique</h1>
        </div>
        <div class="container">
            <form class="form-horizontal" name='musicAdd' action="tracklist.php" method="post">
                    <?php                    
                        session_start();
                        if (isset($_SESSION['username'])) {
                            echo '<div class="form-group">'
                                    . '<label class="control-label col-sm-3" for="user">Votre utilisateur :</label>'
                                    . '<div class="col-sm-9">'
                                    .   '<input id="username" class="form-control" type="text" name="user" placeholder="Utilisateur" required pattern=".{1,255}" disabled value='.$_SESSION['username'].'>'
                                    . '</div>'
                                  . '</div>';              
                        } else {
                            echo '<div class="form-group">'
                                    . '<label class="control-label col-sm-3" for="username">Entrez votre utilisateur :</label>'
                                    . '<div class="col-sm-9"><'
                                    .   'input id="username" class="form-control" type="text" name="username" placeholder="Utilisateur" required pattern=".{1,255}">'
                                    . '</div>'
                                  . '</div>';
                            echo '<div class="form-group">'
                                    . '<label class="control-label col-sm-3" for="password">Entrez votre mot de passe :</label>'
                                    . '<div class="col-sm-9">'
                                    .   '<input id="password" class="form-control" type="password" name="password" placeholder="Mot de passe" required pattern=".{1,255}">'
                                    . '</div>'
                                  . '</div>';
                        }
                    ?>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="title">Entrez le titre :</label>
                    <div class="col-sm-9">
                        <input id="title" class="form-control" type="text" name="title" placeholder="Titre" required pattern=".{0,255}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="author">Entrez l'auteur :</label>
                    <div class="col-sm-9">
                        <input id="author" class="form-control" type="text" name="author" placeholder="Auteur" required pattern=".{0,255}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="year">Entrez l'année :</label>
                    <div class="col-sm-9">
                        <input id="year" class="form-control" type="number" name="year" placeholder="Année" required pattern="[0-9]{4}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="length">Entrez la durée :</label>
                    <div class="col-sm-9">
                        <input id="length" class="form-control" type="number" name="length" placeholder="Durée en [s]" required pattern="[0-9]{0,4}">
                    </div>
                </div>
                <div class="form-group">
                    <?php
                        if (isset($_SESSION['username'])) {
                            echo '<input type="Button" onclick="location.href=\'logout.php\'" name="logout" value="Logout" class="btn btn-danger btn-lg col-sm-3">';
                        } else {
                            echo '<div class="col-sm-3"></div>';
                        }
                    ?>
                    <input type="Submit" name="submit" class="btn btn-primary btn-lg col-sm-9">
                </div>
            </form>
        </div>
    </body>
</html>
