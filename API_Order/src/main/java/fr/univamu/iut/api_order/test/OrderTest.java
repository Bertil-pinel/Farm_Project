package fr.univamu.iut.api_order.test;

import fr.univamu.iut.api_order.Order;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderTest {

    @Test
    public void setValidateTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        o.setValidate(true);
        assertEquals(o.isValidate(), true);
    }

    @Test
    public void isValidateTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        assertEquals(o.isValidate(),false);
    }

    @Test
    public void getIdOrderTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        assertEquals(o.getIdOrder(),1);
    }

    @Test
    public void setRecapOrderTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        o.setRecapOrder("banane");
        assertEquals(o.getRecapOrder(),"banane");
    }

    @Test
    public void getRecapOrderTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        assertEquals(o.getRecapOrder(), "Oeuf, Saumon, Toamtes");
    }

    @Test
    public void getOrderPriceTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        assertEquals(o.getOrderPrice(),50);
    }

    @Test
    public void getRelayPlaceTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        assertEquals(o.getRelayPlace(),"Aix");
    }

    @Test
    public void getOrderDateTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        assertEquals(o.getOrderDate(),"20-05-2023");
    }

    @Test
    public void setIdOrderTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        o.setIdOrder(4);
        assertEquals(o.getIdOrder(),4);
    }

    @Test
    public void setOrderPriceTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        o.setOrderPrice(100);
        assertEquals(o.getOrderPrice(),100);
    }

    @Test
    public void setRelayPlaceTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        o.setRelayPlace("Marseille");
        assertEquals(o.getRelayPlace(),"Marseille");
    }

    @Test
    public void setOrderDateTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        o.setOrderDate("18-04-2024");
        assertEquals(o.getOrderDate(),"18-04-2024");
    }

    @Test
    public void toStringTest() {
        Order o = new Order(1,50,"Aix","20-05-2023",false,"Oeuf, Saumon, Toamtes");
        assertEquals(o.toString(),"Order{idOrder=1, orderPrice=50, relayPlace='Aix', orderDate=20-05-2023, isValidate=false}");
    }
}
