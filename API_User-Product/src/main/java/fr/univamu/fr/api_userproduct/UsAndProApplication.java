package fr.univamu.fr.api_userproduct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


/**
 * Classe principale de l'API User and Product
 */

@ApplicationPath("/api")
@ApplicationScoped
public class UsAndProApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     * @return un objet implémentant l'interface UsAndProRepositoryInterface utilisée
     *          pour accéder aux données des produits et utilisateurs.
     */

    @Produces
    private UsAndProDBInterface openDbConnection(){
        UsAndProRepositoryDB db = null;

        try{
            db = new UsAndProRepositoryDB("jdbc:mariadb://mysql-pinel-guinard.alwaysdata.net/pinel-guinard_user-products", "295723_user", "bertil123");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }


    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     * @param UsProRepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes UsAndProDBInterface UsProRepo ) {
        UsProRepo.close();
    }
}
