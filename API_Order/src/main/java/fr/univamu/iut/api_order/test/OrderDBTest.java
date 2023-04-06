package fr.univamu.iut.api_order.test;

import fr.univamu.iut.api_order.OrderDB;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class OrderDBTest {

    @Test
    public void createOrderTest(){
        try {
            OrderDB odb = new OrderDB("jdbc:mariadb://mysql-delesvaux.alwaysdata.net/delesvaux_order", "delesvaux", "ApiOrder1234");
            assertEquals(odb.createOrder(50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes"),true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void deleteOrderTest(){
        try {
            OrderDB odb = new OrderDB("jdbc:mariadb://mysql-delesvaux.alwaysdata.net/delesvaux_order", "delesvaux", "ApiOrder1234");
            assertEquals(odb.deleteOrder(1),true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void validateOrderTest(){
        try {
            OrderDB odb = new OrderDB("jdbc:mariadb://mysql-delesvaux.alwaysdata.net/delesvaux_order", "delesvaux", "ApiOrder1234");
            assertEquals(odb.validateOrder(1),true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
