<?php

namespace TautofBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

class ModelController extends Controller {

    /**
     * @Route("/api/models", name="apiModels")
     */
    public function modelsAction(Request $request) {
        $make_id = $request->query->get('make_id');

        $repo = $this->getDoctrine()->getRepository('TautofBundle:Model');

        if ($make_id && $make_id > -1) {
            $models = $repo->findBy(array('make' => $make_id));
        } else {
            $models = $repo->findAll();
        }

        return DefaultController::serializeJSON($models, array('make'));
    }

    /**
     * @Route("/api/model", name="apiModel")
     */
    public function modelAction(Request $request) {
        
        $model_id = $request->query->get('model_id');

        $repo = $this->getDoctrine()->getRepository('TautofBundle:Model');

        $model = $repo->findOneBy(array('id' => $model_id));

        return DefaultController::serializeJSON($model);
    }
    
    /**
     * @Route("/api/models/adverts", name="apiModelsAdverts")
     */
    public function modelsAdvertsAction(Request $request) {
        
        $make_id = $request->query->get('make_id');

        $repo = $this->getDoctrine()->getRepository('TautofBundle:Model');

        $model = $repo->findFromAdverts($make_id);

        return DefaultController::serializeJSON($model);
    }
}
