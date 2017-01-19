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
            <form class="" name='musicAdd' action="tracklist.php" method="post">
                <div class="form-group">
                    <label class="control-label" for="title">Entrez le titre :</label>
                    <input id="title" class="form-control" type="text" name="title" placeholder="Title" required pattern=".{0,255}">
                </div>
                <div class="form-group">
                    <label class="control-label" for="author">Entrez l'auteur :</label>
                    <input id="author" class="form-control" type="text" name="author" placeholder="Author" required pattern=".{0,255}">
                </div>
                <div class="form-group">
                    <label class="control-label" for="year">Entrez l'année :</label>
                    <input id="year" class="form-control" type="number" name="year" placeholder="Year" required pattern="[0-9]{4}">
                </div>
                <div class="form-group">
                    <label class="control-label" for="length">Entrez la durée :</label>
                    <input id="length" class="form-control" type="number" name="length" placeholder="Length in [s]" required pattern="[0-9]{0,4}">
                </div>
                <div class="checkbox">
                    <label class="control-label"><input type="checkbox" name="clear" > Effacer la session</label>
                </div>
                <input type="Submit" name="submit" class="btn btn-primary btn-lg btn-block">
            </form>
        </div>
    </body>
</html>
