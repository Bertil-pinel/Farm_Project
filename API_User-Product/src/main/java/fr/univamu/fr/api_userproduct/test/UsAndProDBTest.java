package fr.univamu.fr.api_userproduct.test;

import fr.univamu.fr.api_userproduct.Category;
import fr.univamu.fr.api_userproduct.UsAndProRepositoryDB;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.AssertJUnit.assertEquals;

public class UsAndProDBTest {

    @Test
    public void getUserTest(){
        try {
            UsAndProRepositoryDB userDB = new UsAndProRepositoryDB("jdbc:mariadb://mysql-pinel-guinard.alwaysdata.net/pinel-guinard_user-products", "295723_user", "bertil123");
            assertEquals(userDB.getUser("bertil@mail").getMail(),"bertil@mail");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void getProductTest(){
        try {
            UsAndProRepositoryDB userDB = new UsAndProRepositoryDB("jdbc:mariadb://mysql-pinel-guinard.alwaysdata.net/pinel-guinard_user-products", "295723_user", "bertil123");
            assertEquals(userDB.getProduct("Saumon").getCategory(), Category.SeaFood);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
