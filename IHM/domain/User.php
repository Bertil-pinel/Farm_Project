<?php

namespace domain;
class User
{
    protected $username;
    protected $mail;
    protected $password;
    protected $dateOfCreation;

    public function __construct($username,$mail, $password,$dateOfCreation)
    {
        $this->username = $username;
        $this->mail = $mail;
        $this->password = $password;
        $this->dateOfCreation = $dateOfCreation;
    }

    public function getUsername()
    {
        return $this->username;
    }

    public function getMail()
    {
        return $this->mail;
    }

    public function getPassword()
    {
        return $this->password;
    }

    public function getDateOfCreation()
    {
        return $this->getDateOfCreation;
    }
}