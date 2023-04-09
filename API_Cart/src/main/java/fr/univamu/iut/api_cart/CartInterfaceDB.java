package fr.univamu.iut.api_cart;

import fr.univamu.iut.api_cart.UserNProduct.Product;

import java.util.ArrayList;

/**
 *
 */
public interface CartInterfaceDB {

    /**
     *
     */
    void close();

    /**
     *
     * @param idCart
     * @return
     */
    Cart getCart( int idCart);

    /**
     *
     * @return
     */
    ArrayList<Cart> getAllCart() ;

    boolean createCart(String idUser);

    boolean deleteCart(int idCart);

    boolean addProduct(Cart cart, Product product);

    boolean removeProduct(Cart cart, Product product);

}
