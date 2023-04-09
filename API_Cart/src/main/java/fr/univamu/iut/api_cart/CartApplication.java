package fr.univamu.iut.api_cart;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class CartApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     * @return un objet implémentant l'interface BookRepositoryInterface utilisée
     *          pour accéder aux données des livres, voire les modifier
     */
    @Produces
    private CartInterfaceDB openDbConnection(){
        CartDB db = null;

        try{
            db = new CartDB("jdbc:mariadb://mysql-monterin.alwaysdata.net/monterin_panier_db", "monterin_panier", "mdp.2003");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     *
     * @param cartRepo
     */
    private void closeDbConnection(@Disposes CartInterfaceDB cartRepo ) {
        cartRepo.close();
    }
}