package fr.univamu.iut.api_order;

import java.util.ArrayList;

/**
 * Interface de la classe commande Base de données
 */
public interface OrderInterfaceDB {

    /**
     * Ferme une connexion
     */
    public void close();

    /**
     * Récupère une commandes selon son ID de la base de données
     * @param idOrder
     * @return une commande
     */
    public Order getOrder( int idOrder );

    /**
     * Récupère toutes les commandes de la base données
     * @return ArrayList de commandes
     */
    public ArrayList<Order> getAllOrder() ;

    /**
     * Créer une commande dans la base de données
     * @param orderPrice
     * @param relayPlace
     * @param orderDate
     * @param isValidate
     * @param recapOrder
     * @return boolean oui si la requête à fonctionner non dans le cas contraire
     */
    public boolean createOrder(int orderPrice, String relayPlace, String orderDate, boolean isValidate, String recapOrder);

    /**
     * Supprime une commande de la base de données
     * @param idOrder
     * @return boolean oui si la requête à fonctionner non dans le cas contraire
     */
    public boolean deleteOrder(int idOrder);

    /**
     * Valide une commande de la base de données
     * @param idOrder
     * @return boolean oui si la requête à fonctionner non dans le cas contraire
     */
    public boolean validateOrder(int idOrder);

}
