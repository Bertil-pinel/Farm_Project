package fr.univamu.iut.api_order.test;

import fr.univamu.iut.api_order.Cart;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartTest {

    @Test
    public void getFunctionsTest() {
        Cart c = new Cart(1,50,"20-02-2023","Saumon, Oeuf", "Bob");
        assertEquals(c.getAmountPrice(),50 );
        assertEquals(c.getIdProduct(), "Saumon, Oeuf");
        assertEquals(c.getIdUser(), "Bob");
        assertEquals(c.getModifDate(), "20-02-2023");
        assertEquals(c.getIdCart(), 1);
    }

    @Test
    public void setFunctionsTest() {
        Cart c = new Cart(1,50,"20-02-2023","Saumon, Oeuf", "Bob");
        c.setAmountPrice(20);
        assertEquals(c.getAmountPrice(),20 );
        c.setIdProduct("Toamte, Salade");
        assertEquals(c.getIdProduct(), "Toamte, Salade");
        c.setIdUser("Toto");
        assertEquals(c.getIdUser(), "Toto");
        c.setModifDate("18-11-2021");
        assertEquals(c.getModifDate(), "18-11-2021");
        c.setIdCart(26);
        assertEquals(c.getIdCart(), 26);
    }

    @Test
    public void toStringTest() {
        Cart c = new Cart(1,50,"20-02-2023","Saumon, Oeuf", "Bob");
        assertEquals(c.toString(), "Cart{idCart=1, amountPrice=50, modifDate='20-02-2023', idProduct='Saumon, Oeuf', idUser='Bob'}" );
    }
}
