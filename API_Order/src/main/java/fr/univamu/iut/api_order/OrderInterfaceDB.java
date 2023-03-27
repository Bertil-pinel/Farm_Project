package fr.univamu.iut.api_order;

import java.util.ArrayList;

/**
 *
 */
public interface OrderInterfaceDB {

    /**
     *
     */
    public void close();

    /**
     *
     * @param idOrder
     * @return
     */
    public Order getOrder( int idOrder );

    /**
     *
     * @return
     */
    public ArrayList<Order> getAllOrder() ;


}
