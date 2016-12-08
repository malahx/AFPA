<?php /*%%SmartyHeaderCode:482868055584956886cd932-66332405%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_valid = $_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'fe25e55be002adb888926f550f8e6330118b8496' => 
    array (
      0 => '/var/www/html/Jarditou/themes/default-bootstrap/modules/blockstore/blockstore.tpl',
      1 => 1478514348,
      2 => 'file',
    ),
  ),
  'nocache_hash' => '482868055584956886cd932-66332405',
  'variables' => 
  array (
    'link' => 0,
    'module_dir' => 0,
    'store_img' => 0,
    'store_text' => 0,
  ),
  'has_nocache_code' => false,
  'version' => 'Smarty-3.1.19',
  'unifunc' => 'content_58495688709bf0_12192182',
  'cache_lifetime' => 31536000,
),true); /*/%%SmartyHeaderCode%%*/?>
<?php if ($_valid && !is_callable('content_58495688709bf0_12192182')) {function content_58495688709bf0_12192182($_smarty_tpl) {?>
<!-- Block stores module -->
<div id="stores_block_left" class="block">
	<p class="title_block">
		<a href="http://localhost/Jarditou/index.php?controller=stores" title="Nos magasins">
			Nos magasins
		</a>
	</p>
	<div class="block_content blockstore">
		<p class="store_image">
			<a href="http://localhost/Jarditou/index.php?controller=stores" title="Nos magasins">
				<img class="img-responsive" src="http://localhost/Jarditou/modules/blockstore/store.jpg" alt="Nos magasins" />
			</a>
		</p>
				<div>
			<a 
			class="btn btn-default button button-small" 
			href="http://localhost/Jarditou/index.php?controller=stores" 
			title="Nos magasins">
				<span>Découvrez nos magasins<i class="icon-chevron-right right"></i></span>
			</a>
		</div>
	</div>
</div>
<!-- /Block stores module -->
<?php }} ?>
