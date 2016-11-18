<?php

namespace TautofBundle\Form;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Doctrine\ORM\EntityRepository;

class AdvertType extends AbstractType {

    private $make_id;

    public function buildForm(FormBuilderInterface $builder, array $options) {
        $this->make_id = $options['make_id'];
        $builder
                ->add('model', EntityType::class, array(
                    'label' => false, 
                    'disabled' => $this->make_id != -1 ? false : true, 
                    'required' => true,
                    'placeholder' => 'Modèle',
                    'class' => 'TautofBundle:Model',
                    'query_builder' => function (EntityRepository $er) {
                        if ($this->make_id == -1) {
                            return $er->createQueryBuilder('m');
                        }
                        return $er->createQueryBuilder('m')->where('m.make = :make_id')->setParameter (':make_id', $this->make_id);
                    },
                    'choice_label' => 'name'))
                ->add('color', null, array('label' => false, 'disabled' => $this->make_id != -1 ? false : true, 'required' => true, 'placeholder' => 'Couleur'))
                ->add('year', TextType::class, array('label' => false, 'disabled' => $this->make_id != -1 ? false : true, 'required' => true, 'attr' => array('placeholder' => 'Année')))
                ->add('title', TextType::class, array('label' => false, 'disabled' => $this->make_id != -1 ? false : true, 'required' => true, 'attr' => array('placeholder' => 'Titre')))
                ->add('descr', TextType::class, array('label' => false, 'disabled' => $this->make_id != -1 ? false : true, 'required' => true, 'attr' => array('placeholder' => 'Description')))
                ->add('cost', TextType::class, array('label' => false, 'disabled' => $this->make_id != -1 ? false : true, 'required' => true, 'attr' => array('placeholder' => 'Prix de vente')))
                ->add('km', TextType::class, array('label' => false, 'disabled' => $this->make_id != -1 ? false : true, 'required' => true, 'attr' => array('placeholder' => 'Kilométrage')))
                ->add('pic1', FileType::class, array('label' => false, 'disabled' => $this->make_id != -1 ? false : true, 'required' => true, 'attr' => array('placeholder' => 'Photo 1')))
                ->add('pic2', FileType::class, array('label' => false, 'disabled' => $this->make_id != -1 ? false : true, 'required' => false, 'attr' => array('placeholder' => 'Photo 2')))
                ->add('pic3', FileType::class, array('label' => false, 'disabled' => $this->make_id != -1 ? false : true, 'required' => false, 'attr' => array('placeholder' => 'Photo 3')))
                ->add('advertadd', SubmitType::class);
    }

    public function configureOptions(OptionsResolver $resolver) {
        $resolver->setDefaults(array(
            'make_id' => -1,
        ));
    }

}

/*se Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Doctrine\ORM\EntityRepository;
 * 
 * , EntityType::class, array(
                    'required' => true,
                    'class' => 'TautofBundle:Model',
                    'query_builder' => function (EntityRepository $er) {
                        return $er->createQueryBuilder('m')->where('m.make = '.$this->makeid);
                    },
                    'choice_label' => 'name')*/