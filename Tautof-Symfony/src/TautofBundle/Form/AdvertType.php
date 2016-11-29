<?php

namespace TautofBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\CallbackTransformer;
use Symfony\Component\HttpFoundation\File\File;

class AdvertType extends AbstractType {

    
    public function buildForm(FormBuilderInterface $builder, array $options) {
        $builder->add('model', EntityType::class, array(
                    'label' => false,
                    'required' => true,
                    'placeholder' => 'Modèle',
                    'class' => 'TautofBundle:Model'))
                ->add('color', null, array('label' => false, 'required' => true, 'placeholder' => 'Couleur'))
                ->add('year', TextType::class, array('label' => false, 'required' => true, 'attr' => array('placeholder' => 'Année')))
                ->add('title', TextType::class, array('label' => false, 'required' => true, 'attr' => array('placeholder' => 'Titre')))
                ->add('descr', TextType::class, array('label' => false, 'required' => true, 'attr' => array('placeholder' => 'Description')))
                ->add('cost', TextType::class, array('label' => false, 'required' => true, 'attr' => array('placeholder' => 'Prix de vente')))
                ->add('km', TextType::class, array('label' => false, 'required' => true, 'attr' => array('placeholder' => 'Kilométrage')))
                ->add('pic1', FileType::class, array('label' => 'Photo 1 :', 'required' => false, 'attr' => array('placeholder' => 'Photo 1')))
                ->add('pic2', FileType::class, array('label' => 'Photo 2 :', 'required' => false, 'attr' => array('placeholder' => 'Photo 2')))
                ->add('pic3', FileType::class, array('label' => 'Photo 3 :', 'required' => false, 'attr' => array('placeholder' => 'Photo 3')))
                ->add('advertadd', SubmitType::class);
    }

}
