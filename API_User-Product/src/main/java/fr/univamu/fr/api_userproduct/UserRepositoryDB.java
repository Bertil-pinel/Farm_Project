package fr.univamu.fr.api_userproduct;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class UserRepositoryDB implements UserDBInterface, Closeable {

    /**
     * Session accesing the data
     */
    protected Connection dbConnection ;


    /**
     * Constructor
     * @param infoConnection informations needed to connect to mysql
     * @param user Identifier to connect to mysql
     * @param pwd password to connect to mysql
     */
    public UserRepositoryDB(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Product getProduct(String name) {
        Product selectedProduct = null;

        String query = "SELECT * FROM Product WHERE name=?";

        // build and exec prepared request
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, name);

            // execution of the query
            ResultSet result = ps.executeQuery();

            // Receiving the first and only tuple
            if( result.next() )
            {
                float costPerKilos = result.getFloat("costPerKilos");
                float itemCost = result.getFloat("itemCost");
                Category category = Category.valueOf(result.getString("Category"));
                int disponibility = result.getInt("disponibility");

                // création et initialisation de l'objet Book
                selectedProduct = new Product(name,costPerKilos, itemCost, category,disponibility);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedProduct;
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> listProducts ;

        String query = "SELECT * FROM `Product`";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listProducts = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                String name = result.getString("name");
                float costPerKilos = result.getFloat("costPerKilos");
                float itemCost = result.getFloat("itemCost");
                Category category = Category.valueOf(result.getString("Category"));
                int disponibility = result.getInt("disponibility");

                // création du livre courant
                Product currentPRoduct = new Product(name,costPerKilos, itemCost, category,disponibility);

                listProducts.add(currentPRoduct);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProducts;
    }


    @Override
    public User getUser(String Mail) {
        User selectedUser = null;

        String query = "SELECT * FROM User WHERE mail=?";

        // build and exec prepared request
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, Mail);

            // execution of the query
            ResultSet result = ps.executeQuery();

            // Receiving the first and only tuple
            if( result.next() )
            {
                String username = result.getString("username");
                String mail = result.getString("mail");
                String password = result.getString("password");
                String dateOfCreation = result.getString("dateOfCreation");

                // création et initialisation de l'objet Book
                selectedUser = new User(username,mail, password, dateOfCreation);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUser;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> listUsers ;

        String query = "SELECT * FROM `User`";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listUsers = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                String username = result.getString("username");
                String mail = result.getString("mail");
                String password = result.getString("password");
                String dateOfCreation = result.getString("dateOfCreation");

                // création du livre courant
                User currentUser = new User(username,mail, password, dateOfCreation);

                listUsers.add(currentUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUsers;
    }
}
