<?php
namespace control;

class Controllers
{

    public function  authenticateAction($userCheck, $dataUsers){

        if( !isset($_SESSION['login']) ) {

            if( isset($_POST['login']) && isset($_POST['password']) )
            {
                if( !$userCheck->authenticate($_POST['login'], $_POST['password'], $dataUsers) )
                {
                    $error = 'bad login or pwd';
                    return $error;

                }
                else {
                    $_SESSION['login'] = $_POST['login'] ;
                }
            }
            else{
                $error = 'not connected';
                return $error;
            }

        }
    }

    public function ProductsAction($data, $AppCheck)
    {
        $AppCheck->getAllProducts($data);
    }

    public function ProductAction($name, $data, $AppCheck)
    {
        $AppCheck->getProduct($name, $data);
    }

}