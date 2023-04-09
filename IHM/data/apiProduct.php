<?php

namespace data;

use service\ProductsAccessInterface;
include_once "service/ProductsAccessInterface.php";

use domain\User;
include_once "domain/User.php";

use domain\Product;
include_once "domain/Product.php";

class apiProduct implements ProductsAccessInterface
{
   public function getAllProducts(){

        $url = "http://localhost:8080/API_User-Product-1.0-SNAPSHOT/api/products";

        $curlConnection  = curl_init();

        $response = curl_exec($curlConnection);
        curl_close($curlConnection);

        if( !$response )
            echo curl_error($curlConnection);

        $response = json_decode( $response, true );


        $products = array();
        foreach ( $response as $product){

            $name = $product['name'];
            $costPerKilos = $product['costPerKilos'];
            $itemCost = $product['itemCost'];
            $category= $product['category'];
            $disponibility = $product['disponibility'];

            $currentPost = new Product($name, $costPerKilos, $itemCost, $category, $disponibility);
            $products[$name] = $currentPost;
        }

        return $products;
    }

    public function getProduct($name){

        $url = "http://localhost:8080/API_User-Product-1.0-SNAPSHOT/api/products" + $name;

        $curlConnection  = curl_init();

        $response = curl_exec($curlConnection);
        curl_close($curlConnection);

        if( !$response )
            echo curl_error($curlConnection);

        $response = json_decode( $response, true );


            $name = $response['name'];
            $costPerKilos = $response['costPerKilos'];
            $itemCost = $response['itemCost'];
            $category= $response['category'];
            $disponibility = $response['disponibility'];

        return new Product($name, $costPerKilos, $itemCost, $category, $disponibility);
    }

}