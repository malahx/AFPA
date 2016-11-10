<?php

// Redirection pour un utilisateur non login
if (!isLogin()) {
    refresh();
}

require_once(dirname(__FILE__) . '/view.inc.php');

view::head();
view::navbar($action);

echo '  <table class="table table-hover">
            <thead>
                <tr>' . (isset($playlistId) ? '<th></th>' : '') . '
                    <th>Nom du genre</th>
                    <th class="text-center">Courte description</th>
                    <th class="text-center">Liens</th>
                </tr>
            </thead>
            <tbody>';

// Création de chaque élement du tableau
foreach ($genres as $genre) {
    echo '  <tr class="cursorDefault">
                <td>' . $genre->getName() . ' (' . $genre->getId() . ')</td>
                <td class="text-center">' . $genre->getDesc() . '</td>
                <td class="text-center">' . $genre->getUrl() . '</td>
            </tr>';
}

// Fermeture du tableau
echo '      </tbody>
        </table>';

view::modals();
view::footer();