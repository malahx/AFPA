<?php /*%%SmartyHeaderCode:85746985258495688275cc9-48920673%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_valid = $_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '7085f028f85bcfb75679dc698270dd853e2df2e6' => 
    array (
      0 => '/var/www/html/Jarditou/themes/default-bootstrap/modules/blockcategories/blockcategories.tpl',
      1 => 1478514348,
      2 => 'file',
    ),
    'd0eebf096c0e9fd830a167a1e27629b780486800' => 
    array (
      0 => '/var/www/html/Jarditou/themes/default-bootstrap/modules/blockcategories/category-tree-branch.tpl',
      1 => 1478514348,
      2 => 'file',
    ),
  ),
  'nocache_hash' => '85746985258495688275cc9-48920673',
  'variables' => 
  array (
    'blockCategTree' => 0,
    'currentCategory' => 0,
    'isDhtml' => 0,
    'child' => 0,
  ),
  'has_nocache_code' => false,
  'version' => 'Smarty-3.1.19',
  'unifunc' => 'content_584956882b8c30_58066111',
  'cache_lifetime' => 31536000,
),true); /*/%%SmartyHeaderCode%%*/?>
<?php if ($_valid && !is_callable('content_584956882b8c30_58066111')) {function content_584956882b8c30_58066111($_smarty_tpl) {?><!-- Block categories module -->
<div id="categories_block_left" class="block">
	<h2 class="title_block">
					Catégories
			</h2>
	<div class="block_content">
		<ul class="tree dhtml">
												
<li >
	<a 
	href="http://localhost/Jarditou/index.php?id_category=14&amp;controller=category&amp;id_lang=1" title="Outil en tout genre">
		Outillage
	</a>
			<ul>
												
<li >
	<a 
	href="http://localhost/Jarditou/index.php?id_category=15&amp;controller=category&amp;id_lang=1" title="Outillage manuelle, silencieux et écologique">
		Outillage manuel
	</a>
	</li>

																
<li class="last">
	<a 
	href="http://localhost/Jarditou/index.php?id_category=16&amp;controller=category&amp;id_lang=1" title="Outillage à moteur, bruyant et polyant">
		Outillage à moteur
	</a>
	</li>

									</ul>
	</li>

																
<li >
	<a 
	href="http://localhost/Jarditou/index.php?id_category=17&amp;controller=category&amp;id_lang=1" title="Plants et graines en tout genre">
		Plants et semis
	</a>
	</li>

																
<li >
	<a 
	href="http://localhost/Jarditou/index.php?id_category=18&amp;controller=category&amp;id_lang=1" title="Arbres en tout genre">
		Arbres et Arbustes
	</a>
	</li>

																
<li >
	<a 
	href="http://localhost/Jarditou/index.php?id_category=19&amp;controller=category&amp;id_lang=1" title="Pots et accessoires de toutes formes et de toutes dimensions">
		Pots et accessoires
	</a>
	</li>

																
<li >
	<a 
	href="http://localhost/Jarditou/index.php?id_category=20&amp;controller=category&amp;id_lang=1" title="Mobilier et équipements pour un jardin accessible">
		Mobilier et Equipements de jardin
	</a>
	</li>

																
<li class="last">
	<a 
	href="http://localhost/Jarditou/index.php?id_category=21&amp;controller=category&amp;id_lang=1" title="Matériaux de jardin">
		Matériaux
	</a>
	</li>

									</ul>
	</div>
</div>
<!-- /Block categories module -->
<?php }} ?>
