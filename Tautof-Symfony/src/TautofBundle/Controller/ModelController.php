<?php

namespace TautofBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class ModelController extends Controller {

    /**
     * @Route("/models", name="models")
     */
    public function modelsAction(Request $request) {

        // Voir https://symfony.com/doc/current/components/serializer.html
        $make_id = $request->query->get('make_id');
        $repo = $this->getDoctrine()->getRepository('TautofBundle:Model');

        if ($make_id) {
            $models = $repo->findBy(array('make' => $make_id));
        } else {
            $models = $repo->findAll();
        }

        $encoders = array(new JsonEncoder());

        $normalizer = new ObjectNormalizer();
        $normalizer->setIgnoredAttributes(array('make'));
        $normalizers = array($normalizer);

        $serializer = new Serializer($normalizers, $encoders);

        $json = $serializer->serialize($models, 'json');

        $response = new Response($json);
        $response->headers->set('Content-Type', 'application/json');
        return $response;
    }

    /**
     * @Route("/modelMake", name="modelMake")
     */
    public function modelMakeAction(Request $request) {

        $model_id = $request->query->get('model_id');
        $repo = $this->getDoctrine()->getRepository('TautofBundle:Model');

        $model = $repo->findOneBy(array('id' => $model_id));

        return new Response($model->getMake()->getId());
    }

}
