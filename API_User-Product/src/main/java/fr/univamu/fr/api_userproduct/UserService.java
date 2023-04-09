package fr.univamu.fr.api_userproduct;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'éccès aux données)
 */
public class UserService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les commandes
     */
    protected UsAndProDBInterface UserRepositoryDB ;

    /**
     * Constructeur
     * @param UserRepositoryDB
     */
    public  UserService( UsAndProDBInterface UserRepositoryDB) {
        this.UserRepositoryDB = UserRepositoryDB;
    }


    /**
     * Méthode retournant tout les utilisateurs sous format JSON
     * @return String
     */
    public String getAllUsersJSON(){

        ArrayList<User> allUsers = UserRepositoryDB.getAllUsers();

        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allUsers);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant l'utilisateur possédant cet email sous format JSON
     * @return String
     */
    public String getUserJSON( String mail ){
        String result = null;
        User myUser = UserRepositoryDB.getUser(mail);

        if( myUser != null ) {

            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUser);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode retournant tout les produits sous format JSON
     * @return String
     */

    public String getAllProductsJSON(){

        ArrayList<Product> allProducts = UserRepositoryDB.getAllProducts();

        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allProducts);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }
    /**
     * Méthode retournant le produit portant ce nom sous format JSON
     * @return String
     */

    public String getProdcutJSON( String name ){
        String result = null;
        Product myProduct = UserRepositoryDB.getProduct(name);

        if( myProduct != null ) {

            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myProduct);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }


    /**
     * Create a new User
     * @param username
     * @param mail
     * @param password
     * @return
     */
    public boolean createUser(String username, String mail, String password){
        boolean result = false;

        result = UserRepositoryDB.createUser(username,mail,password);

        return result;
    }
}
