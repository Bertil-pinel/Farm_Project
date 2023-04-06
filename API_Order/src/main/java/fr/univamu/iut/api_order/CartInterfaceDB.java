package fr.univamu.iut.api_order;

import java.util.ArrayList;

/**
 * Interface de la classe Panier Api Base de données
 */
public interface CartInterfaceDB {

    /**
     * ferme la connexion à l'API
     */
    public void close();

    /**
     * Méthode permettant de récupérer un panier selon sa référence
     * @param idCart
     * @return un panier
     */
    public Cart getCart( int idCart);

    /**
     * Méthode permettant de récupérer tous les paniers
     * @return ArrayList de panier
     */
    public ArrayList<Cart> getAllCart() ;

}
