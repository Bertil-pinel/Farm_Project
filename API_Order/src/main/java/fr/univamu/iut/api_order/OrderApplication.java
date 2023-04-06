package fr.univamu.iut.api_order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Classe principale de l'API commande
 */
@ApplicationPath("/api")
@ApplicationScoped
public class OrderApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     * @return un objet implémentant l'interface OrderRepositoryInterface utilisée
     *          pour accéder aux données des commandes, les valider, supprimer ...
     */
    @Produces
    private OrderInterfaceDB openDbConnection(){
        OrderDB db = null;

        try{
            db = new OrderDB("jdbc:mariadb://mysql-delesvaux.alwaysdata.net/delesvaux_order", "delesvaux", "ApiOrder1234");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    @Produces
    private CartInterfaceDB connectCartApi(){
        return new CartDBApi("http://localhost:8080/Api_Cart-1.0-SNAPSHOT/api/");
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     * @param orderRepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes OrderInterfaceDB orderRepo ) {
        orderRepo.close();
    }
}