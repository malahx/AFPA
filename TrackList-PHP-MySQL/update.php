<?php

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

require ("config.php");
session_start();
if (!isset($_SESSION['userid'])) {
    session_destroy();
    header('Location: index.php');
    die();
}
$id =       filter_input(INPUT_GET, 'id',      FILTER_SANITIZE_NUMBER_INT);
$title =    filter_input(INPUT_GET, 'title',   FILTER_SANITIZE_SPECIAL_CHARS);
$author =   filter_input(INPUT_GET, 'author',  FILTER_SANITIZE_SPECIAL_CHARS);
$year =     filter_input(INPUT_GET, 'year',    FILTER_SANITIZE_NUMBER_INT);
$length =   filter_input(INPUT_GET, 'length',  FILTER_SANITIZE_NUMBER_INT);
?>
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
            <h1>Modifier une musique</h1>
        </div>
        <div class="container">
            <form class="form-horizontal" name='musicAdd' action="tracklist.php" method="post">
                <div class="form-group hide">
                    <label class="control-label col-sm-3" for="title">ID de la musique :</label>
                    <div class="col-sm-9">
                        <input id="title" class="form-control" type="number" name="id" placeholder="Id" value="<?php echo $id ?>" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="title">Modifiez le titre :</label>
                    <div class="col-sm-9">
                        <input id="title" class="form-control" type="text" name="title" placeholder="Titre" value="<?php echo $title ?>" required pattern=".{0,255}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="author">Modifiez l'auteur :</label>
                    <div class="col-sm-9">
                        <input id="author" class="form-control" type="text" name="author" placeholder="Auteur" value="<?php echo $author ?>" required pattern=".{0,255}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="year">Modifiez l'année :</label>
                    <div class="col-sm-9">
                        <input id="year" class="form-control" type="number" name="year" placeholder="Année" value="<?php echo $year ?>" required pattern="[0-9]{4}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-3" for="length">Modifiez la durée :</label>
                    <div class="col-sm-9">
                        <input id="length" class="form-control" type="number" name="length" placeholder="Durée en [s]" value="<?php echo $length ?>" required pattern="[0-9]{0,4}">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-3"></div>
                    <input type="Submit" name="submit" class="btn btn-primary btn-lg col-sm-9">
                </div>
            </form>
        </div>
    </body>
</html>