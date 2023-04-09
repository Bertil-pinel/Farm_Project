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
     * Objets permettant d'accéder au dépôt où sont stockées les informations sur les Users et les Products et les Carts
     */
    protected CartInterfaceDB cartRepo ;

    /**
     * Objets permettant d'accéder au dépôt où sont stockées les informations sur les Carts
     */
    protected UsAndProDBInterface usAndProRepo;

    /**
     * Un boolean pour connaitre la réussite des méthodes qui l'utilisent
     */
    protected boolean result = false;

    /**
     * Constructeur de CartService
     * @param cartRepo CartInterfaceDB permettant d'accéder au dépôt où sont stockées les informations sur les Users et les Products et les Carts
     * @param usAndProRepo UsAndProDBInterface permettant d'accéder au dépôt où sont stockées les informations sur les Carts
     */
    public CartService(CartInterfaceDB cartRepo, UsAndProDBInterface usAndProRepo) {
        this.cartRepo = cartRepo;
        this.usAndProRepo = usAndProRepo;
    }

    /**
     * Méthode retournant les informations sur tous les paniers de la BDD au format JSON
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
     * Méthode retournant les informations sur un panier souhaité de la BDD au format JSON
     * @param idCart Int visant le panier souhaité
     * @return une chaîne de caractère contenant les informations au format JSON
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
     * @param mail String visant l'utilisateur qui crée le panier
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
     * @param idCart Int visant le panier souhaité
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
     * @param cart Cart visant le panier souhaité pour ajouter le product à l'objet java
     * @param product Product visant le produit souhaité
     * @return un boolean pour vérifier la réussite de la fonction
     */
    public boolean addProduct(Cart cart, Product product ){

        if(cartRepo.getCart(cart.getIdCart()) == null){
            throw new NotFoundException("Cart not exists");
        }else{
            this.result = cartRepo.addProduct(cart, product);
            cart.setIdProduct(product.getName());
        }
        return this.result;
    }

    /**
     * Méthode qui supprime un produit dans un panief
     * @param cart Cart visant le panier souhaité pour supprimer le product à l'objet java
     * @param product Product visant le produit souhaité
     * @return un boolean pour vérifier la réussite de la fonction
     */
    public boolean removeProduct(Cart cart, Product product){

        if(cartRepo.getCart(cart.getIdCart()) == null){
            throw new NotFoundException("Cart not exists");
        }else{
            this.result = cartRepo.removeProduct(cart, product);
            cart.setIdProduct(null);
        }
        return this.result;
    }

}

