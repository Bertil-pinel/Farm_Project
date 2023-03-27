package fr.univamu.iut.api_cart;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'éccès aux données)
 */
public class CartService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les livres
     */
    protected CartInterfaceDB cartRepo ;

    public CartService(CartInterfaceDB cartRepo) {
        this.cartRepo = cartRepo;
    }

    /**
     * Méthode retournant les informations sur les livres au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllCartsJSON(){

        ArrayList<Cart> allCarts = cartRepo.getAllCart();

        // création du json et conversion de la liste de livres
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allCarts);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     *
     * @param idUser
     * @param idProduct
     * @return
     */
    public String getCartJSON( String idUser, String idProduct ){
        String result = null;
        Cart myCart = cartRepo.getCart(idUser,idProduct);

        // si le livre a été trouvé
        if( myCart != null ) {

            // création du json et conversion du livre
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myCart);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

}

