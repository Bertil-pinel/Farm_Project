<?php

namespace service;
class AppChecking
{
    protected $productsTxt;
    protected $UsersTxt;

    public function getProductTxt($data, $name)
    {
        $product = $data->getProduct($name);

        $this->productsTxt[] = ['name' => $product->getName(), 'costPerKilos' => $product->getCostPerKilos(),'itemCost' => $product->getItemCost(),'category' => $product->getCategory(), 'disponibility' => $product->getDisponibility()];

    }

    public function getAllProducts($data)
    {
        $products = $data->getAllProducts();

        $this->productsTxt = array();
        foreach ($products as $product) {
            $this->productsTxt[] = ['name' => $product->getName(), 'costPerKilos' => $product->getCostPerKilos(),'itemCost' => $product->getItemCost(),'category' => $product->getCategory(), 'disponibility' => $product->getDisponibility()];
        }
    }

    public function getUsersTxt()
    {
        return $this->UsersTxt;
    }

    public function getAllUsers($data)
    {
        $Users = $data->getAllUsers();

        $this->UsersTxt = array();
        foreach ($Users as $user) {
            $this->UsersTxt[] = ['username' => $user->getUsername(), 'mail' => $user->getMail(),'password' => $user->getPassword(),'dateOfCreation' => $user->getDateOfCreation()];
        }
    }

    public function authenticate($login,$password,$data){
        $User = $data->getUser($login);
        return $User->getPassword()==$password;
    }
}