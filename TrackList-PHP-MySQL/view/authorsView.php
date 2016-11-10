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
                <tr>
                    <th>Nom de l\'autheur</th>
                </tr>
            </thead>
            <tbody>';

// Création de chaque élement du tableau
foreach ($authors as $author) {
    echo '  <tr class="cursorDefault">
                <td>' . $author->getName() . ' (' . $author->getId() . ')</td>
            </tr>';
}

// Fermeture du tableau
echo '      </tbody>
        </table>';

view::modals();
view::footer();


