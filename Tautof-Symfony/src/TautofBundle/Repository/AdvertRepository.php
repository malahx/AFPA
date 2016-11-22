<?php

namespace TautofBundle\Repository;

use Doctrine\ORM\EntityRepository;

class AdvertRepository extends EntityRepository {

    public function findAllMakes() {
        $qb = $this->createQueryBuilder('a')
                ->join('a.model', 'mo')
                ->join('mo.make', 'ma')
                ->select('ma.id,ma.name')
                ->groupby('ma.id');
        return $qb->getQuery()->getResult();
    }

    public function findAllModels($make_id = -1) {
        $qb = $this->createQueryBuilder('a')
                ->join('a.model', 'mo')
                ->join('mo.make', 'ma')
                ->select('mo.id,mo.name,ma.id make_id')
                ->groupby('mo.id');
        if ($make_id > -1) {
            $qb->where('ma.id = :make_id')
                    ->setParameter('make_id', $make_id);
        }
        return $qb->getQuery()->getResult();
    }

    public function filterByMake($make_id) {
        $qb = $this->createQueryBuilder('a')
                ->join('a.model', 'mo')
                ->join('mo.make', 'ma')
                ->where('ma.id = :make_id')
                ->setParameter('make_id', $make_id);
        return $qb->getQuery()->getResult();
    }

    public function filterByModel($model_id) {
        $qb = $this->createQueryBuilder('a')
                ->join('a.model', 'mo')
                ->join('mo.make', 'ma')
                ->where('mo.id = :model_id')
                ->setParameter('model_id', $model_id);
        return $qb->getQuery()->getResult();
    }

}
