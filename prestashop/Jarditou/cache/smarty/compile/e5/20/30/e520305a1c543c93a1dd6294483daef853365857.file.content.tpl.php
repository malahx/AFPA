<?php /* Smarty version Smarty-3.1.19, created on 2016-12-07 08:36:56
         compiled from "/var/www/html/Jarditou/admin751zem5p1/themes/default/template/content.tpl" */ ?>
<?php /*%%SmartyHeaderCode:499178660584810787f0257-41253892%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_valid = $_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'e520305a1c543c93a1dd6294483daef853365857' => 
    array (
      0 => '/var/www/html/Jarditou/admin751zem5p1/themes/default/template/content.tpl',
      1 => 1478514344,
      2 => 'file',
    ),
  ),
  'nocache_hash' => '499178660584810787f0257-41253892',
  'function' => 
  array (
  ),
  'variables' => 
  array (
    'content' => 0,
  ),
  'has_nocache_code' => false,
  'version' => 'Smarty-3.1.19',
  'unifunc' => 'content_584810787f5619_80093944',
),false); /*/%%SmartyHeaderCode%%*/?>
<?php if ($_valid && !is_callable('content_584810787f5619_80093944')) {function content_584810787f5619_80093944($_smarty_tpl) {?>
<div id="ajax_confirmation" class="alert alert-success hide"></div>

<div id="ajaxBox" style="display:none"></div>


<div class="row">
	<div class="col-lg-12">
		<?php if (isset($_smarty_tpl->tpl_vars['content']->value)) {?>
			<?php echo $_smarty_tpl->tpl_vars['content']->value;?>

		<?php }?>
	</div>
</div><?php }} ?>
