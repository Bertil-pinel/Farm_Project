package fr.univamu.fr.api_userproduct.test;

import fr.univamu.fr.api_userproduct.Category;
import fr.univamu.fr.api_userproduct.Product;
import fr.univamu.fr.api_userproduct.User;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class ProductTest {

    @Test
    public void getFunctionsTest() {
        Product product = new Product("patate",1,(float) 0.1, Category.Vegetables, 10);
        assertEquals(product.getName(),"patate" );
        assertEquals(product.getCostPerKilos(),(float) 1.0 );
        assertEquals(product.getItemCost(),(float) 0.1);
        assertEquals(product.getCategory(),Category.Vegetables );
        assertEquals(product.getDisponibility(),10 );
    }

    @Test
    public void setFunctionsTest() {
        Product product = new Product("patate",1,(float) 0.1, Category.Vegetables, 10);
        product.setName("potato");
        assertEquals(product.getName(),"potato" );
        product.setCostPerKilos((float) 2.0);
        assertEquals(product.getCostPerKilos(),(float) 2.0 );
        product.setItemCost((float) 1.0 );
        assertEquals(product.getItemCost(),(float) 1.0);
        product.setCategory(Category.DairyProduct);
        assertEquals(product.getCategory(),Category.DairyProduct );
        product.setDisponibility(11);
        assertEquals(product.getDisponibility(),11 );
    }

    @Test
    public void toStringTest() {
        Product product = new Product("patate",1,(float) 0.1, Category.Vegetables, 10);
        assertEquals(product.toString(), "Product{name='patate', costPerKilos=1.0, itemCost=0.1, category=Vegetables, disponibility=10}" );
    }
}
