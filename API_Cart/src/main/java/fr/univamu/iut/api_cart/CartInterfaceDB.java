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
     * @param idCart
     * @return
     */
    public Cart getCart( int idCart);

    /**
     *
     * @return
     */
    public ArrayList<Cart> getAllCart() ;


}
