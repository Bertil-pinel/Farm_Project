package fr.univamu.fr.api_userproduct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("/api")
@ApplicationScoped
public class UserApplication extends Application {


    @Produces
    private UserDBInterface openDbConnection(){
        UserRepositoryDB db = null;

        try{
            db = new UserRepositoryDB("jdbc:mariadb://mysql-pinel-guinard.alwaysdata.net/pinel-guinard_user-products", "295723_user", "bertil123");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }


    private void closeDbConnection(@Disposes UserDBInterface User ) {
        User.close();
    }
}
