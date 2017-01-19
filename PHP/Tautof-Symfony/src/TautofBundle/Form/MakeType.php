<?php

namespace TautofBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;

class MakeType extends AbstractType {


    public function buildForm(FormBuilderInterface $builder, array $options) {
        $builder->add('name', EntityType::class, array('required' => true, 'label' => false, 'placeholder' => 'Marque', 'class' => 'TautofBundle:Make', 'choice_label' => 'name'));
    }
}
