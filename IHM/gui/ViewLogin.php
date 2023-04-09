<?php
namespace gui;

include_once "View.php";

class ViewLogin extends View
{
    public function __construct($layout)
    {
        parent::__construct($layout);

        $this->title = 'Farm Connexion';

        $this->content = '
            <form method="post" action="/index.php/products">
                <label for="login"> Votre identifiant </label> :
                <input type="text" name="login" id="login" placeholder="defaut" required />
                <br />
                <label for="password"> Votre mot de passe </label> :
                <input type="password" name="password" id="password" required />
        
                <input type="submit" value="Envoyer">
            </form>
            
            <a href="/Farm/index.php/create">Création d\'un nouveau compte</a>
            ';
    }
}