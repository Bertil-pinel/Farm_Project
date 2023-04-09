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

    public function getUser($login){

        $apiUrl="http://localhost:8080/API_User-Product-1.0-SNAPSHOT/api/users/" . (string) $login;

        $curlConnection  = curl_init($apiUrl);

        $response = curl_exec($curlConnection);

        if( !$response )
            echo curl_error($curlConnection);

        curl_close($curlConnection);

        $response = json_decode( $response, true );


        $username = isset($response['username']) ? $response['username'] : 0;
        $password = isset($response['password']) ? $response['password'] : 0;
        $dateOfCreation = isset($response['dateOfCreation']) ? $response['dateOfCreation'] : 0;


        return new User($username, $login, $password, $dateOfCreation);

    }


}