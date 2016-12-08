<?php /*%%SmartyHeaderCode:915992067584812ed09abb5-90484868%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_valid = $_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '70b70b54905d15f6872047c926b214bdeb009f42' => 
    array (
      0 => '/var/www/html/Jarditou/themes/default-bootstrap/modules/blockcms/blockcms.tpl',
      1 => 1478514348,
      2 => 'file',
    ),
  ),
  'nocache_hash' => '915992067584812ed09abb5-90484868',
  'version' => 'Smarty-3.1.19',
  'unifunc' => 'content_58495688a2a000_57142746',
  'has_nocache_code' => true,
  'cache_lifetime' => 31536000,
),true); /*/%%SmartyHeaderCode%%*/?>
<?php if ($_valid && !is_callable('content_58495688a2a000_57142746')) {function content_58495688a2a000_57142746($_smarty_tpl) {?>
	<!-- Block CMS module footer -->
	<section class="footer-block col-xs-12 col-sm-2" id="block_various_links_footer">
		<h4>Informations</h4>
		<ul class="toggle-footer">
							<li class="item">
					<a href="http://localhost/Jarditou/index.php?controller=prices-drop" title="Promotions">
						Promotions
					</a>
				</li>
									<li class="item">
				<a href="http://localhost/Jarditou/index.php?controller=new-products" title="Nouveaux produits">
					Nouveaux produits
				</a>
			</li>
										<li class="item">
					<a href="http://localhost/Jarditou/index.php?controller=best-sales" title="Meilleures ventes">
						Meilleures ventes
					</a>
				</li>
										<li class="item">
					<a href="http://localhost/Jarditou/index.php?controller=stores" title="Nos magasins">
						Nos magasins
					</a>
				</li>
									<li class="item">
				<a href="http://localhost/Jarditou/index.php?controller=contact" title="Contactez-nous">
					Contactez-nous
				</a>
			</li>
															<li class="item">
						<a href="http://localhost/Jarditou/index.php?id_cms=3&amp;controller=cms&amp;id_lang=1" title="Conditions d&#039;utilisation">
							Conditions d&#039;utilisation
						</a>
					</li>
																<li class="item">
						<a href="http://localhost/Jarditou/index.php?id_cms=4&amp;controller=cms&amp;id_lang=1" title="A propos">
							A propos
						</a>
					</li>
													<li>
				<a href="http://localhost/Jarditou/index.php?controller=sitemap" title="sitemap">
					sitemap
				</a>
			</li>
					</ul>
		
	</section>
		<section class="bottom-footer col-xs-12">
		<div>
			<?php echo smartyTranslate(array('s'=>'[1] %3$s %2$s - Ecommerce software by %1$s [/1]','mod'=>'blockcms','sprintf'=>array('PrestaShop™',date('Y'),'©'),'tags'=>array('<a class="_blank" href="http://www.prestashop.com">')),$_smarty_tpl);?>

		</div>
	</section>
		<!-- /Block CMS module footer -->
<?php }} ?>
