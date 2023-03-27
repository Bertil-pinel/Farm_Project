package fr.univamu.iut.api_cart;

import java.util.ArrayList;

/**
 *
 */
public interface CartInterfaceDB {

    /**
     *
     */
    public void close();

    /**
     *
     * @param idUser
     * @param isProduct
     * @return
     */
    public Cart getCart( String idUser, String isProduct);

    /**
     *
     * @return
     */
    public ArrayList<Cart> getAllCart() ;


}
