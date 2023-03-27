package fr.univamu.fr.api_userproduct;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

public class UserService {

    protected UsAndProDBInterface UserRepositoryDB ;


    public  UserService( UsAndProDBInterface UserRepositoryDB) {
        this.UserRepositoryDB = UserRepositoryDB;
    }



    public String getAllUsersJSON(){

        ArrayList<User> allUsers = UserRepositoryDB.getAllUsers();

        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allUsers);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    public String getUserJSON( String mail ){
        String result = null;
        User myUser = UserRepositoryDB.getUser(mail);

        if( myUser != null ) {

            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUser);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    public String getAllProductsJSON(){

        ArrayList<Product> allProducts = UserRepositoryDB.getAllProducts();

        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allProducts);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    public String getProdcutJSON( String name ){
        String result = null;
        Product myProduct = UserRepositoryDB.getProduct(name);

        if( myProduct != null ) {

            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myProduct);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }
}
