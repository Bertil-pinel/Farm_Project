<?php

// charge et initialise les bibliothèques globales
include_once 'data/apiProduct.php';
include_once 'data/apiUser.php';

include_once 'control/Controllers.php';
include_once 'control/Presenter.php';

include_once 'service/AppChecking.php';

include_once 'gui/Layout.php';
include_once 'gui/ViewProducts.php';


use gui\{Layout,ViewProducts};
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

// intialisation du cas d'utilisation service\AnnoncesChecking
$AppCheck = new AppChecking() ;

// intialisation du presenter avec accès aux données de AnnoncesCheking
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
        $uri='/TD2/index.php/error' ;
        if( $error == 'bad login or pwd' or $error == 'not connected')
            $redirect = '/TD2/index.php';
    }
}

// route la requête en interne
// i.e. lance le bon contrôleur en fonction de la requête effectuée
if ( '/TD2/' == $uri || '/TD2/index.php' == $uri || '/TD2/index.php/logout' == $uri) {
    // affichage de la page de connexion

    session_destroy();
    $layout = new Layout("gui/layoutLogged.html" );
    $vueLogin = new ViewLogin( $layout );

    $vueLogin->display();
}
elseif ( '/TD2/index.php/annonces' == $uri ){

    if(isset($_POST['contractType'])){
        $controller->annonceCreationAction($_SESSION['login'], $_POST,$dataAnnonces,$annonceCreation);
    }

    // affichage de toutes les annonces

    $controller->annoncesAction($dataAnnonces, $AppCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vueAnnonces= new ViewAnnonces( $layout,  $_SESSION['login'], $presenter);

    $vueAnnonces->display();
}
elseif ( '/TD2/index.php/post' == $uri
    && isset($_GET['id'])) {
    // Affichage d'une annonce

    $controller->postAction($_GET['id'], $dataAnnonces, $AppCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vuePost= new ViewPost( $layout, $presenter );

    $vuePost->display();
}

elseif ( '/TD2/index.php/annoncesAlternance' == $uri ){
    // Affichage de toutes les entreprises offrant de l'alternance

    $controller->annoncesAction($apiAlternance, $AppCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vueAnnoncesAlternance= new ViewAnnoncesAlternance( $layout,  $_SESSION['login'], $presenter);

    $vueAnnoncesAlternance->display();
}

else {
    header('Status: 404 Not Found');
    echo '<html><body><h1>My Page NotFound</h1></body></html>';
}

?>