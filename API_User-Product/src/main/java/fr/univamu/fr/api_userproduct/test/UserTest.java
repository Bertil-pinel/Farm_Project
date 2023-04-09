package fr.univamu.fr.api_userproduct.test;

import fr.univamu.fr.api_userproduct.User;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class UserTest {

    @Test
    public void getFunctionsTest() {
        User user = new User("bobo","bob@gmail.com","Supersecret","10-04-2023");
        assertEquals(user.getUsername(),"bobo" );
        assertEquals(user.getMail(), "bob@gmail.com");
        assertEquals(user.getPassword(), "Supersecret");
        assertEquals(user.getDateOfCreation(), "10-04-2023");
    }

    @Test
    public void setFunctionsTest() {
        User user = new User("bobo","bob@gmail.com","Supersecret","10-04-2023");
        user.setUsername("boba");
        assertEquals(user.getUsername(),"boba" );
        user.setMail("bob@gmail.");
        assertEquals(user.getMail(), "bob@gmail.");
        user.setPassword("Supersec");
        assertEquals(user.getPassword(), "Supersec");
    }

    @Test
    public void toStringTest() {
        User user = new User("bobo","bob@gmail.com","Supersecret","10-04-2023");
        assertEquals(user.toString(), "User{username='bobo', mail='bob@gmail.com', password='Supersecret', dateOfCreation='10-04-2023'}" );
    }
}
