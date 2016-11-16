<?php

namespace TautofBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;

class DefaultController extends Controller
{
    
    /**
     * @Route("/", name="home")
     */
    public function homeAction()
    {
        $repo = $this->getDoctrine()->getRepository('TautofBundle:Advert');
        $adverts = $repo->findAll();
        return $this->render('TautofBundle::home.html.twig', array ('title' => 'Tautof Annonces', 'adverts' => $adverts));
    }
    /**
     * @Route("/adverts", name="adverts")
     */
    public function advertsAction()
    {
        $repo = $this->getDoctrine()->getRepository('TautofBundle:Advert');
        $adverts = $repo->findAll();
        return $this->render('TautofBundle::adverts.html.twig', array ('title' => 'Tautof Annonces', 'adverts' => $adverts));
    }
    /**
     * @Route("/advert/{id}", name="advert")
     */
    public function advertAction($id)
    {
        $repo = $this->getDoctrine()->getRepository('TautofBundle:Advert');
        $advert = $repo->findOneBy(array('id' => $id));
        return $this->render('TautofBundle::advert.html.twig', array ('title' => 'Tautof Annonces', 'advert' => $advert));
    }
}
