package fr.univamu.iut.api_cart;

import fr.univamu.iut.api_cart.UserNProduct.Product;
import fr.univamu.iut.api_cart.UserNProduct.UsAndProDBInterface;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'accès aux données)
 */
public class CartService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les Users, les Products et les Carts
     */
    protected CartInterfaceDB cartRepo ;
    protected UsAndProDBInterface usAndProRepo;

    protected boolean result = false;

    public CartService(CartInterfaceDB cartRepo, UsAndProDBInterface usAndProRepo) {
        this.cartRepo = cartRepo;
        this.usAndProRepo = usAndProRepo;
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
     * @param idCart
     * @return
     */
    public String getCartJSON( int idCart ){
        String result = null;
        Cart myCart = cartRepo.getCart(idCart);

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

    /**
     * Méthode qui créer un panier
     * @return un boolean pour vérifier la réussite de la fonction
     */
    public boolean createCart( String mail ){

        if(usAndProRepo.getUser(mail) == null ){
            throw new NotFoundException("User not exists");
        }else{
            this.result = cartRepo.createCart(mail);
        }
        return this.result;
    }

    /**
     * Méthode qui supprime un panier
     * @return un boolean pour vérifier la réussite de la fonction
     */
    public boolean deleteCart(int idCart){

        if(cartRepo.getCart(idCart) == null ){
            throw new NotFoundException("Cart not exists");
        }else{
            this.result = cartRepo.deleteCart(idCart);
        }
        return this.result;
    }

    /**
     * Méthode qui ajoute un produit dans un panier
     * @return un boolean pour vérifier la réussite de la fonction
     */
    public boolean addProduct(int idCart, Product product ){

        if(cartRepo.getCart(idCart) == null){
            throw new NotFoundException("Cart not exists");
        }else{
            this.result = cartRepo.addProduct(idCart, product);
        }
        return this.result;
    }

    /**
     * Méthode qui supprime un produit dans un panier
     * @return un boolean pour vérifier la réussite de la fonction
     */
    public boolean removeProduct(int idCart, Product product){

        if(cartRepo.getCart(idCart) == null){
            throw new NotFoundException("Cart not exists");
        }else{
            this.result = cartRepo.removeProduct(idCart, product);
        }
        return this.result;
    }

}

