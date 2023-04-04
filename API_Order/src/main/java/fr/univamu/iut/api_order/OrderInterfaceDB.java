package fr.univamu.iut.api_order;

import java.sql.Date;
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

    public boolean createOrder(float orderPrice, String relayPlace, String orderDate, boolean isValidate, String recapOrder);

    public boolean deleteOrder(int idOrder);

    public boolean validateOrder(int idOrder);

}
