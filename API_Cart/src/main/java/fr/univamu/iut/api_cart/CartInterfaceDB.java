package fr.univamu.iut.api_cart;

import fr.univamu.iut.api_cart.UserNProduct.Product;

import java.util.ArrayList;

/**
 * Interface créant les méthodes pour la classe CartDB
 */
public interface CartInterfaceDB {

    /**
     * Méthode permettant de fermer l'accès à la BDD
     */
    void close();

    /**
     * Méthode permettant d'obtenir les informations d'un panier souhaité de la BDD
     * @param idCart Int visant le panier souhaité
     * @return le panier souhaité
     */
    Cart getCart( int idCart);

    /**
     * Méthode permettant d'obtenir les informations de tous les paniers de la BDD
     * @return la liste de tous les paniers
     */
    ArrayList<Cart> getAllCart() ;

    /**
     * Méthode qui créer un panier
     * @param idUser String visant l'utilisateur qui créer le panier
     * @return un boolean pour vérifier la réussite de la fonction
     */
    boolean createCart(String idUser);

    /**
     * Méthode qui supprime un panier
     * @param idCart Int visant le panier souhaité
     * @return un boolean pour vérifier la réussite de la fonction
     */
    boolean deleteCart(int idCart);

    /**
     * Méthode qui ajoute un produit au panier souhaité
     * @param cart Cart visant le panier souhaité
     * @param product Product visant le produit souhaité
     * @return un boolean pour vérifier la réussite de la fonction
     */
    boolean addProduct(Cart cart, Product product);

    /**
     * Méthode qui supprime un produit au panier souhaité
     * @param cart Cart visant le panier souhaité
     * @param product Product visant le produit souhaité
     * @return un boolean pour vérifier la réussite de la fonction
     */
    boolean removeProduct(Cart cart, Product product);

}
