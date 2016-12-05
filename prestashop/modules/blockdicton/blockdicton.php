<?php

if (!defined('_PS_VERSION_')) {
    exit;
}

class BlockDicton extends Module {

    public function __construct() {

        $this->name = 'blockdicton';
        $this->tab = 'front_office_features';
        
        $this->version = '1.0';
        $this->author = 'Gwénolé LE HENAFF';
        
        $this->need_instance = 0;
        
        $this->ps_versions_compliancy = array(
            'min' => '1.5',
            'max' => _PS_VERSION_
        );
        
        parent::__construct();
        
        $this->displayName = $this->l('Dicton');
        $this->description = $this->l('Un jour, Un dicton !');
        $this->confirmUninstall = $this->l('Êtes vous certain de vouloir désinstaller ?');
        
    }

    public function install() {

        Configuration::updateValue('BLOCKDICTON_TABLE', 'dicton');

        $table = Configuration::get('BLOCKDICTON_TABLE');
        
        $created = Db::getInstance()->Execute('CREATE TABLE ' . _DB_PREFIX_ . $table . ' (
            `mois` int(11) NOT NULL,
            `jour` int(11) NOT NULL,
            `saint` varchar(255) NOT NULL,
            `genre` boolean NOT NULL,
            `dicton` text NOT NULL,
            `conseil` text NOT NULL
            ) ENGINE=InnoDB DEFAULT CHARSET=latin1');
        
        if ($created) {
            
            Db::getInstance()->insert($table, array(
                'mois' => 12,
                'jour' => 5,
                'saint' => "Samson",
                'genre' => 1,
                'dicton' => "Il est trop tard pour acheter du bois, quand l\'homme souffle sur ses doigts.",
                'conseil' => "Il est temps de planter des arbres :)"
            ));
            
            Db::getInstance()->insert($table, array(
                'mois' => 12,
                'jour' => 6,
                'saint' => 'Malo',
                'genre' => 1,
                'dicton' => "Qui voit Ouessant voit son sang.",
                'conseil' => "Il est temps de planter des fleurs :)"
            ));
            
            Db::getInstance()->insert($table, array(
                'mois' => 12,
                'jour' => 7,
                'saint' => 'Brieuc',
                'genre' => 1,
                'dicton' => "Mieux vaut sagesse que richesse.",
                'conseil' => "Il est temps de planter des pommiers :)"
            ));
            
        }
        
        return parent::install() &&
                $this->registerHook('leftColumn') &&
                $this->registerHook('header') && $created;
    }

    public function uninstall() {

        $table = _DB_PREFIX_ . Configuration::get('BLOCKDICTON_TABLE');
        
        $parent = parent::uninstall();
        
        if ($parent) {
            $deleted = Db::getInstance()->Execute('DROP TABLE ' . $table);
        }
        
        return $parent && configuration::deleteByName('BLOCKDICTON_TABLE') && $deleted;
    }

    public function hookDisplayLeftColumn($params) {
        
        $table = _DB_PREFIX_ . Configuration::get('BLOCKDICTON_TABLE');
        
        $mois = (int) date('n');
        $jour = (int) date('j');
        
        $sql = 'SELECT * FROM ' . $table . ' WHERE mois = ' . $mois . ' AND jour = ' . $jour;
        
        if ($row = Db::getInstance()->getRow($sql)) {

            $saint = ($row['genre'] ? 'Saint' : 'Sainte') . ' ' . $row['saint'];

            $this->context->smarty->assign(array(
                'saint' => $saint,
                'dicton' => $row['dicton'],
                'conseil' => $row['conseil']
            ));
        }

        return $this->display(__FILE__, 'blockdicton.tpl');
    }

    public function hookDisplayRightColumn($params) {

        return $this->hookDisplayLeftColumn($params);
    }

    public function hookDisplayHeader() {

        $this->context->controller->addCSS($this->_path . 'views/css/blockdicton.css', 'all');
        
    }

}

//Configuration::get('maVariable')
//Configuration::updateValue('MYMODULE_NAME', 'my friend') 
//Configuration::getMultiple(array('maPremiereVariable', 'maSecondeVariable', 'maTroisiemeVariable'))
//Configuration::deleteByName('myVariable')
// Conf de la langue : Configuration::get('PS_LANG_DEFAULT')
