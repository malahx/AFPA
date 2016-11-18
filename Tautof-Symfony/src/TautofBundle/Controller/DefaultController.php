<?php

namespace TautofBundle\Controller;

use TautofBundle\Entity\Advert;
use TautofBundle\Form\AdvertType;
use TautofBundle\Form\MakeType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller {

    /**
     * @Route("/", name="home")
     */
    public function homeAction() {
        $repo = $this->getDoctrine()->getRepository('TautofBundle:Advert');
        $adverts = $repo->findAll();
        return $this->render('TautofBundle::home.html.twig', array('title' => 'Tautof Annonces', 'adverts' => $adverts));
    }

    /**
     * @Route("/adverts", name="adverts")
     */
    public function advertsAction() {
        $repo = $this->getDoctrine()->getRepository('TautofBundle:Advert');
        $adverts = $repo->findAll();
        return $this->render('TautofBundle::adverts.html.twig', array('title' => 'Tautof Annonces', 'adverts' => $adverts));
    }

    /**
     * @Route("/advert/{id}", name="advert")
     */
    public function advertAction($id) {
        $repo = $this->getDoctrine()->getRepository('TautofBundle:Advert');
        $advert = $repo->findOneBy(array('id' => $id));
        return $this->render('TautofBundle::advert.html.twig', array('title' => 'Tautof Annonces', 'advert' => $advert));
    }

    /**
     * @Route("/advertadd{make_id}", name="advertadd", defaults={"make_id" = -1})
     */
    public function advertAddAction($make_id, Request $request) {
        if (!$this->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            return $this->redirectToRoute('home');
        }
        $repo = $this->getDoctrine()->getRepository('TautofBundle:Make');
        $make = $repo->findOneBy(array('id' => $make_id));
        $makeForm = $this->createForm(MakeType::class, $make, array('make' => $make));
        $advert = new Advert();
        $advertForm = $this->createForm(AdvertType::class, $advert, array('make_id' => $make_id));
        $advertForm->handleRequest($request);
        if ($advertForm->isSubmitted() && $advertForm->isValid()) {
            $advert = $advertForm->getData();
            $em = $this->getDoctrine()->getManager();
            $em->persist($advert);
            $em->flush();
             return $this->redirectToRoute('advert', array('id' => $advert->getId()));
        }
        return $this->render('TautofBundle::advertAdd.html.twig', array('title' => 'Tautof Annonces', 'advertadd' => $advertForm->createView(), 'make' => $makeForm->createView()));
    }

}
