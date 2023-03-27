<?php

namespace data;

use service\UsersAccessInterface;
include_once "service/UsersAccessInterface.php";

use domain\User;
include_once "domain/User.php";


class apiUser implements UsersAccessInterface
{

    public function getAllUsers(){


        $apiUrl = "http://localhost:8080/API_User-Product-1.0-SNAPSHOT/api/users";

        $curlConnection  = curl_init();

        $response = curl_exec($curlConnection);
        curl_close($curlConnection);

        if( !$response )
            echo curl_error($curlConnection);

        $response = json_decode( $response, true );

        $users = array();
        foreach ( $response as $user){


            $username = $user['username'];
            $mail = $user['mail'];
            $password = $user['password'];
            $dateOfCreation = $user['dateOfCreation'];


            $currentUser = new User($username, $mail, $password, $dateOfCreation);
            $users[$username] = $currentUser;
        }

        return $users;
    }

    public function getPost($mail){

        $apiUrl = "http://localhost:8080/API_User-Product-1.0-SNAPSHOT/api/users/" + $mail;

        $curlConnection  = curl_init();

        $response = curl_exec($curlConnection);
        curl_close($curlConnection);

        if( !$response )
            echo curl_error($curlConnection);

        $response = json_decode( $response, true );

        $username = $response['username'];
        $mail = $response['mail'];
        $password = $response['password'];
        $dateOfCreation = $response['dateOfCreation'];


        return new User($username, $mail, $password, $dateOfCreation);

    }


}