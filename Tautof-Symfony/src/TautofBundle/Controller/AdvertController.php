<?php

namespace TautofBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Component\HttpFoundation\Request;

class AdvertController extends Controller {

    /**
     * @Route("/api/adverts", name="apiAdverts")
     */
    public function advertsAction(Request $request) {

        $model_id = $request->query->get('model_id');
        $make_id = $request->query->get('make_id');
        $sortby = $request->query->get('sortby');
        $orderby = $request->query->get('orderby');

        $repo = $this->getDoctrine()->getRepository('TautofBundle:Advert');

        if ($model_id && $model_id > -1) {
            $adverts = $repo->filterByModel($model_id, $sortby, $orderby);
        } else if ($make_id && $make_id > -1) {
            $adverts = $repo->filterByMake($make_id, $sortby, $orderby);
        } else {
            $adverts = $repo->SortAllBy($sortby, $orderby);
        }
        return $this->render('TautofBundle:elements:advertList.html.twig', array('adverts' => $adverts));
    }

    /**
     * @Route("/api/adverts/models", name="apiAdvertsModels")
     */
    public function advertsModelsAction(Request $request) {
        $make_id = $request->query->get('make_id');
        
        $repo = $this->getDoctrine()->getRepository('TautofBundle:Advert');

        if ($make_id) {
            $models = $repo->findAllModels($make_id);
        } else {
            $models = $repo->findAllModels();
        }
        return DefaultController::serializeJSON($models);
    }

}
