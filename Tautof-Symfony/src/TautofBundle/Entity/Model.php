<?php

namespace TautofBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Model
 *
 * @ORM\Table(name="model", indexes={@ORM\Index(name="join_marque", columns={"make_id"})})
 * @ORM\Entity(repositoryClass="TautofBundle\Repository\ModelRepository")
 */
class Model {

    /**
     * @var string
     *
     * @ORM\Column(name="name", type="string", length=255, nullable=false)
     */
    private $name;

    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var \TautofBundle\Entity\Make
     *
     * @ORM\ManyToOne(targetEntity="TautofBundle\Entity\Make")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="make_id", referencedColumnName="id")
     * })
     */
    private $make;

    /**
     * Set name
     *
     * @param string $name
     *
     * @return Model
     */
    public function setName($name) {
        $this->name = $name;

        return $this;
    }

    /**
     * Get name
     *
     * @return string
     */
    public function getName() {
        return $this->name;
    }

    /**
     * Get id
     *
     * @return integer
     */
    public function getId() {
        return $this->id;
    }

    /**
     * Set make
     *
     * @param \TautofBundle\Entity\Make $make
     *
     * @return Model
     */
    public function setMake(\TautofBundle\Entity\Make $make = null) {
        $this->make = $make;

        return $this;
    }

    /**
     * Get make
     *
     * @return \TautofBundle\Entity\Make
     */
    public function getMake() {
        return $this->make;
    }

    public function __toString() {
        return $this->getName();
    }

}
