<?php

// charge et initialise les bibliothèques globales
include_once 'data/apiProduct.php';
include_once 'data/apiUser.php';

include_once 'control/Controllers.php';
include_once 'control/Presenter.php';

include_once 'service/AppChecking.php';

include_once 'gui/Layout.php';
include_once 'gui/ViewProducts.php';
include_once 'gui/ViewLogin.php';


use gui\{Layout,ViewProducts,ViewLogin};
use control\{Controllers, Presenter};
use data\{apiUser, apiProduct};
use service\{AppChecking};

$data = null;
try {
    $apiUsers = new apiUser();
    $apiProducts = new apiProduct();
} catch (PDOException $e) {
    print "Erreur de connexion !: " . $e->getMessage() . "<br/>";
    die();
}


// initialisation du controller
$controller = new Controllers();

// intialisation du cas d'utilisation service\AppChecking
$AppCheck = new AppChecking() ;

// intialisation du presenter avec accès aux données de AppChecking
$presenter = new Presenter($AppCheck);

// chemin de l'URL demandée au navigateur
// (p.ex. /TD2/index.php)
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// définition d'une session d'une heure
ini_set('session.gc_maxlifetime', 3600);
session_set_cookie_params(3600);
session_start();
// Authentification et création du compte (sauf pour le formulaire de connexion et la route de déconnexion)
if ( '/Farm/' != $uri and '/Farm/index.php' != $uri and '/Farm/index.php/logout' != $uri ){

    $error = $controller->authenticateAction($AppCheck, $apiUsers);

    if( $error != null )
    {
        $uri='/Farm/index.php/error' ;
        if( $error == 'bad login or pwd' or $error == 'not connected')
            $redirect = '/Farm/index.php';
    }
}

// route la requête en interne
// i.e. lance le bon contrôleur en fonction de la requête effectuée
if ( '/Farm/' == $uri || '/Farm/index.php' == $uri || '/Farm/index.php/logout' == $uri) {
    // affichage de la page de connexion

    session_destroy();
    $layout = new Layout("gui/layoutLogged.html" );
    $vueLogin = new ViewLogin( $layout );

    $vueLogin->display();
}
elseif ( '/index.php/products' == $uri) {
    // Affichage des produits

    $controller->ProductsAction($apiProducts, $AppCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vuePost= new ViewProducts( $layout, $presenter );

    $vuePost->display();
}

elseif ( '/Farm/index.php/product' == $uri
    && isset($_GET['name'])) {
    // Affichage d'un produit

    $controller->ProductAction($_GET['name'], $apiProducts, $AppCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vuePost= new ViewProduct( $layout, $presenter );

    $vuePost->display();
}

else {
    header('Status: 404 Not Found');
    echo '<html><body><h1>My Page NotFound</h1></body></html>';
}

?>