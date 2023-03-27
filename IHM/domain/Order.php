<?php

namespace domain;
class Order
{
    protected $name;
    protected $costPerKilos;
    protected $itemCost;
    protected $category;
    protected $disponibility;

    public function __construct($name, $costPerKilos, $itemCost, $category, $disponibility)
    {
        $this->name = $name;
        $this->costPerKilos = $costPerKilos;
        $this->itemCost = $itemCost;
        $this->category = $category;
        $this->disponibility = $disponibility;
    }

    public function getName()
    {
        return $this->name;
    }

    public function getCostPerKilos()
    {
        return $this->costPerKilos;
    }

    public function getItemCost()
    {
        return $this->itemCost;
    }

    public function getcategory()
    {
        return $this->category;
    }

    public function getDisponibility()
    {
        return $this->disponibility;
    }
}