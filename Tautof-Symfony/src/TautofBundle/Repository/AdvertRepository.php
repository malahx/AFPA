<?php

namespace TautofBundle\Repository;

use Doctrine\ORM\EntityRepository;

class AdvertRepository extends EntityRepository {

    private function sortBy($qb, $sortby, $orderby) {
        switch ($sortby) {
            case "0":
                $sort = 'a.cost';
                break;
            case "1":
                $sort = 'a.km';
                break;
            case "2":
                $sort = 'a.year';
                break;
            default:
                return $qb;
        }
        $qb->orderBy($sort, $orderby);
        return $qb;
    }

    public function findAllMakes() {
        $qb = $this->createQueryBuilder('a')
                ->select('ma.id, ma.name')
                ->join('a.model', 'mo')
                ->join('mo.make', 'ma')
                ->groupby('ma.id');
        return $qb->getQuery()->getResult();
    }

    public function findAllModels($make_id = false) {
        $qb = $this->createQueryBuilder('a')
                ->select('mo.id, mo.name, ma.id make_id')
                ->join('a.model', 'mo')
                ->join('mo.make', 'ma')
                ->groupby('mo.id');
        if ($make_id) {
            $qb->where('ma.id = :make_id')
                    ->setParameter('make_id', $make_id);
        }
        return $qb->getQuery()->getResult();
    }

    public function filterByMake($make_id, $sortby = "-1", $orderby = 'ASC') {
        $qb = $this->createQueryBuilder('a')
                ->join('a.model', 'mo')
                ->join('mo.make', 'ma')
                ->where('ma.id = :make_id')
                ->setParameter('make_id', $make_id);
        
        return AdvertRepository::sortBy($qb, $sortby, $orderby)
                ->getQuery()
                ->getResult();
    }

    public function filterByModel($model_id, $sortby = "-1", $orderby = 'ASC') {
        $qb = $this->createQueryBuilder('a')
                ->join('a.model', 'mo')
                ->join('mo.make', 'ma')
                ->where('mo.id = :model_id')
                ->setParameter('model_id', $model_id);
        
        return AdvertRepository::sortBy($qb, $sortby, $orderby)
                ->getQuery()
                ->getResult();
    }

    public function SortAllBy($sortby, $orderby = 'ASC') {
        $qb = $this->createQueryBuilder('a')
                ->join('a.model', 'mo')
                ->join('mo.make', 'ma');
        
        return AdvertRepository::sortBy($qb, $sortby, $orderby)
                ->getQuery()
                ->getResult();
    }

}
