<div id="<?php echo $modal->getId() ?>" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title text-center"><span class="glyphicon glyphicon-<?php echo $modal->getGlyphicon() ?>"></span> <?php echo $modal->getText() ?></h4>
            </div>
            <div class="modal-body">
                <form role="form" action="<?php echo $modal->getAction() ?>" method="post">
                    <?php echo $modal->renderInputs() ?>
                    <button type="submit" name="<?php echo $modal->getId() ?>" class="btn btn-success btn-block"><span class="glyphicon glyphicon-<?php echo $modal->getGlyphicon() ?>"></span> <?php echo $modal->gettext() ?></button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Annuler</button>
            </div>
        </div>

    </div>
</div>

