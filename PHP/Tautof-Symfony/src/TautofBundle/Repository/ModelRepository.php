<?php

namespace TautofBundle\Repository;

use Doctrine\ORM\EntityRepository;

class ModelRepository extends EntityRepository {

    public function findFromAdverts($make_id = false) {

        $qb = $this->createQueryBuilder('mo')
                ->join('mo.make', 'ma')
                ->leftJoin('TautofBundle\Entity\Advert', 'a', \Doctrine\ORM\Query\Expr\Join::WITH, 'mo = a.model')
                ->where("a.id <> ''");
        if ($make_id && $make_id > -1) {
            $qb->andWhere('ma.id = :make_id')
                    ->setParameter('make_id', $make_id);
        }
        return $qb->getQuery()->getResult();
    }

}
