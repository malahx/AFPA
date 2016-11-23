<?php

namespace TautofBundle\Controller;

use TautofBundle\Entity\Advert;
use TautofBundle\Entity\Make;
use TautofBundle\Form\AdvertType;
use TautofBundle\Form\MakeType;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class DefaultController extends Controller {
    /* STATIC FUNCTIONS */

    static function serializeJSON($obj, $ignore = false) {
        $encoders = array(new JsonEncoder());

        $normalizer = new ObjectNormalizer();

        if ($ignore) {
            $normalizer->setIgnoredAttributes($ignore);
        }

        $normalizers = array($normalizer);

        $serializer = new Serializer($normalizers, $encoders);

        $json = $serializer->serialize($obj, 'json');

        $response = new Response($json);
        $response->setStatusCode(Response::HTTP_OK);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /* OTHERS FUNCTIONS */

    private function picUpload($file) {
        $fileName = md5(uniqid()) . '.' . $file->guessExtension();
        $file->move(DefaultController::uploadDir(), $fileName);
        return DefaultController::uploadDir($fileName);
    }

    private function uploadDir($file = null) {
        if ($file) {
            return $this->getParameter('uploads_directory') . $file;
        }
        return $this->getParameter('root_directory') . $this->getParameter('uploads_directory');
    }

    /* ROUTE FUNCTIONS */

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
        return $this->render('TautofBundle::advert.html.twig', array('title' => 'Tautof Annonces - ' + $advert . title, 'advert' => $advert));
    }

    /**
     * @Route("/advertadd", name="advertadd")
     */
    public function advertAddAction(Request $request) {
        $make = new Make();
        $makeForm = $this->createForm(MakeType::class, $make);
        $advert = new Advert();
        $advertForm = $this->createForm(AdvertType::class, $advert);
        $advertForm->handleRequest($request);
        if ($advertForm->isSubmitted() && $advertForm->isValid()) {
            $advert = $advertForm->getData();

            $user = $this->getUser();
            $advert->setUser($user);

            $advert->setPic1(DefaultController::picUpload($advert->getPic1()));
            $advert->setPic2(DefaultController::picUpload($advert->getPic2()));
            $advert->setPic3(DefaultController::picUpload($advert->getPic3()));

            $em = $this->getDoctrine()->getManager();
            $em->persist($advert);
            $em->flush();
            return $this->redirectToRoute('advert', array('id' => $advert->getId()));
        }
        return $this->render('TautofBundle::advertAdd.html.twig', array('title' => 'Tautof Annonces - Ajouter', 'advertadd' => $advertForm->createView(), 'make' => $makeForm->createView()));
    }

    /**
     * @Route("/advertfilter", name="advertfilter")
     */
    public function advertFilterAction(Request $request) {
        $make_id = $request->get('make_id');
        $model_id = $request->get('model_id');

        $currentModel = null;

        $em = $this->getDoctrine()->getManager();
        $repo = $em->getRepository('TautofBundle:Advert');

        // Liste des marques
        $makes = $repo->findAllMakes();

        // Liste des modèles
        $models = $repo->findAllModels($make_id);

        // Liste des annonces filtrées
        if ($model_id) {
            $repoModel = $em->getRepository('TautofBundle:Model');
            $currentModel = $repoModel->findOneBy(array('id' => $model_id));
            $make_id = $currentModel->getMake()->getid();
            $adverts = $repo->filterByModel($model_id);
        } else if ($make_id) {
            $adverts = $repo->filterByMake($make_id);
        } else {
            $adverts = $repo->findAll();
        }
        return $this->render('TautofBundle::advertFilter.html.twig', array(
                    'title' => 'Tautof Annonces - Filtre',
                    'makes' => $makes,
                    'models' => $models,
                    'adverts' => $adverts,
                    'make_id' => $make_id,
                    'currentModel' => $currentModel));
    }

}
