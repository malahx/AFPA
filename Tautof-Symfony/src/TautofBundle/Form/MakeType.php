<?php

namespace TautofBundle\Form;

use TautofBundle\Entity\Make;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;

class MakeType extends AbstractType {

    private $make;

    public function buildForm(FormBuilderInterface $builder, array $options) {
        $this->make = $options['make'];

        $builder->add('name', EntityType::class, array('required' => true, 'label' => false, 'placeholder' => $options['make'], 'class' => 'TautofBundle:Make', 'choice_label' => 'name'));
    }

    public function configureOptions(OptionsResolver $resolver) {
        $resolver->setDefaults(array(
            'make' => 'Marque',
        ));
    }

}
