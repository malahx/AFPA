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
            $user = $this->getUser();
            $advert->setUser($user);
            $em = $this->getDoctrine()->getManager();
            $em->persist($advert);
            $em->flush();
            return $this->redirectToRoute('advert', array('id' => $advert->getId()));
        }
        return $this->render('TautofBundle::advertAdd.html.twig', array('title' => 'Tautof Annonces', 'advertadd' => $advertForm->createView(), 'make' => $makeForm->createView()));
    }

    /**
     * @Route("/advertfilter", name="advertfilter")
     */
    public function advertFilterAction(Request $request) {
        if (!$this->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            return $this->redirectToRoute('home');
        }
        $make_id = $request->get('make_id');
        if (!$make_id) {
            $make_id = -1;
        }

        $em = $this->getDoctrine()->getManager();

        $repo = $em->getRepository('TautofBundle:Make');
        $makes = $repo->findAll();

        if ($make_id > -1) {
            $repo = $em->getRepository('TautofBundle:Advert');
            $qb = $repo->createQueryBuilder('a')
                    ->join('a.model', 'mo')
                    ->addSelect('mo')
                    ->join('mo.make', 'ma')
                    ->addSelect('ma')
                    ->where('ma.id = :make_id')
                    ->setParameter('make_id', $make_id);
            $adverts = $qb->getQuery()->getResult();
        } else {
            $repo = $em->getRepository('TautofBundle:Advert');
            $adverts = $repo->findAll();
        }
        return $this->render('TautofBundle::advertFilter.html.twig', array('title' => 'Tautof Annonces', 'makes' => $makes, 'adverts' => $adverts, 'make_id' => $make_id));
    }

}
