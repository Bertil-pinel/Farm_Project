package fr.univamu.iut.api_cart.UserNProduct;;

import java.util.*;

/**
 * Interface for accessing Users data
 */

public interface UsAndProDBInterface {

    /**
     *  Method closing data access for Users
     */
    public void close();

    /**
     * Method returning User according to the mail given in parameters
     * @param mail Id of the wanted User
     * @return User object found
     */
    public User getUser( String mail );

    /**
     * Method returning all users
     * @return A list of user
     */
    public ArrayList<User> getAllUsers();

    /**
     * Method returning Product according to the name given in parameters
     * @param name Id of the wanted product
     * @return Product object found
     */
    public Product getProduct(String name);

    /**
     * Method returning all products
     * @return A list of product
     */
    public ArrayList<Product> getAllProducts();

}
