<?php

// charge et initialise les bibliothèques globales
include_once 'data/AnnonceSqlAccess.php';

include_once 'control/Controllers.php';
include_once 'control/Presenter.php';

include_once 'service/AnnoncesChecking.php';
include_once 'service/UserChecking.php';

include_once 'gui/Layout.php';
include_once 'gui/ViewProducts.phpw.php';


use gui\{Layout,ViewProducts};
use control\{Controllers, Presenter};
use data\{AnnonceSqlAccess, UserSqlAccess, ApiAlternance,ApiEmploi};
use service\{AnnoncesChecking, UserChecking,AnnonceCreation};

$data = null;
try {
    $bd = new PDO('mysql:host=mysql-pinel-guinard.alwaysdata.net;dbname=pinel-guinard_annonces_db', '295723_annonces', 'bertil123');
    // construction du modèle
    $dataAnnonces = new AnnonceSqlAccess($bd);
    $apiAlternance = new ApiAlternance($bd);
    $dataUsers = new UserSqlAccess($bd);

} catch (PDOException $e) {
    print "Erreur de connexion !: " . $e->getMessage() . "<br/>";
    die();
}


// initialisation du controller
$controller = new Controllers();

// intialisation du cas d'utilisation service\AnnoncesChecking
$annoncesCheck = new AnnoncesChecking() ;
$annonceCreation = new AnnonceCreation() ;

// intialisation du cas d'utilisation service\UserChecking
$userCheck = new UserChecking() ;

// intialisation du presenter avec accès aux données de AnnoncesCheking
$presenter = new Presenter($annoncesCheck);

// chemin de l'URL demandée au navigateur
// (p.ex. /TD2/index.php)
$uri = parse_url($_SERVER['REQUEST_URI'], PHP_URL_PATH);

// définition d'une session d'une heure
ini_set('session.gc_maxlifetime', 3600);
session_set_cookie_params(3600);
session_start();

// Authentification et création du compte (sauf pour le formulaire de connexion et la route de déconnexion)
if ( '/TD2/' != $uri and '/TD2/index.php' != $uri and '/TD2/index.php/logout' != $uri ){

    $error = $controller->authenticateAction($userCheck, $dataUsers);

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

    $controller->annoncesAction($dataAnnonces, $annoncesCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vueAnnonces= new ViewAnnonces( $layout,  $_SESSION['login'], $presenter);

    $vueAnnonces->display();
}
elseif ( '/TD2/index.php/post' == $uri
    && isset($_GET['id'])) {
    // Affichage d'une annonce

    $controller->postAction($_GET['id'], $dataAnnonces, $annoncesCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vuePost= new ViewPost( $layout, $presenter );

    $vuePost->display();
}
elseif ( '/TD2/index.php/error' == $uri ){
    // Affichage d'un message d'erreur

    $layout = new Layout("gui/layoutLogged.html" );
    $vuePostEmploi = new ViewOffreEmploi( $layout,  $_SESSION['login'], $presenter );

    $vuePostEmploi->display();
}
elseif ( '/TD2/index.php/annoncesEmploi' == $uri ){
    // Affichage de toutes les entreprises offrant de l'alternance

    $controller->annoncesAction($apiEmploi, $annoncesCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vueAnnoncesEmploi= new ViewAnnoncesEmploi( $layout,  $_SESSION['login'], $presenter);

    $vueAnnoncesEmploi->display();
}
elseif ( '/TD2/index.php/offreEmploi' == $uri
    && isset($_GET['id'])) {
    // Affichage d'une annonce

    $controller->postAction($_GET['id'], $apiEmploi, $annoncesCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vuePost= new ViewPost( $layout, $presenter );

    $vuePost->display();
}
elseif ( '/TD2/index.php/annoncesAlternance' == $uri ){
    // Affichage de toutes les entreprises offrant de l'alternance

    $controller->annoncesAction($apiAlternance, $annoncesCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vueAnnoncesAlternance= new ViewAnnoncesAlternance( $layout,  $_SESSION['login'], $presenter);

    $vueAnnoncesAlternance->display();
}
elseif ( '/TD2/index.php/companyAlternance' == $uri
    && isset($_GET['id'])) {
    // Affichage d'une entreprise offrant de l'alternance

    $controller->postAction($_GET['id'], $apiAlternance, $annoncesCheck);

    $layout = new Layout("gui/layoutLogged.html" );
    $vuePostAlternance = new ViewCompanyAlternance( $layout,  $_SESSION['login'], $presenter );

    $vuePostAlternance->display();
}
elseif ( '/TD2/index.php/createAnnonce' == $uri ){

    $layout = new Layout("gui/layoutLogged.html" );
    $vueCreateAnnonce= new ViewCreateAnnonce( $layout);

    $vueCreateAnnonce->display();
}
else {
    header('Status: 404 Not Found');
    echo '<html><body><h1>My Page NotFound</h1></body></html>';
}

?>