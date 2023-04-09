package fr.univamu.iut.api_cart;

import fr.univamu.iut.api_cart.UserNProduct.Product;

import java.sql.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CartDB implements CartInterfaceDB{

    protected Connection dbConnection ;

    public CartDB(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
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
    public Cart getCart(int idCard) {
        Cart selectedCart = null;

        String query = "SELECT * FROM `Cart` WHERE idCart=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
                ps.setInt(1, idCard);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du livre est valide)
            if( result.next() )
            {
                int totalAmount = result.getInt("TotalAmount");
                String dateOfChange = result.getString("DateOfChange");
                String idProduct = result.getString("Product");
                String idUser = result.getString("User");

                // création et initialisation de l'objet Book
                selectedCart = new Cart(idCard,totalAmount,dateOfChange,idProduct,idUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedCart;
    }

    @Override
    public ArrayList<Cart> getAllCart() {
        ArrayList<Cart> listCarts ;

        String query = "SELECT * FROM `Cart`";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listCarts = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {

                int idCart = result.getInt("idCart");
                int totalAmount = result.getInt("TotalAmount");
                String dateOfChange = result.getString("DateOfChange");
                String idProduct = result.getString("Product");
                String idUser = result.getString("User");


                // création du livre courant
                Cart currentOrder = new Cart(idCart, totalAmount, dateOfChange, idProduct,  idUser);

                listCarts.add(currentOrder);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCarts;
    }

    @Override
    public boolean createCart(String mail) {

        String query = "INSERT INTO `Cart` (`idCart`, `User`) VALUES (?)";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, null);
            ps.setString(2, mail);

            // exécution de la requête
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean deleteCart(int idCart) {

        String query = "DELETE FROM `Cart` WHERE idCart=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ps.setInt(1, idCart);
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean addProduct(Cart cart, Product product) {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateCurrent = new Date();

        String dateDB = dateFormat.format(dateCurrent);

        int totalAmount = (int) (cart.getTotalAmount() - product.getItemCost());

        String query = "INSERT INTO `Cart` (`Product`, `DateOfChange`, `TotalAmount`) VALUES (?, ?, ?) WHERE idCart=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1,product.getName());
            ps.setString(2,dateDB);
            ps.setInt(3,totalAmount);
            ps.setInt(4,cart.getIdCart());

            // exécution de la requête
            ps.executeQuery();

            // update du montant du panier
            cart.setTotalAmount(totalAmount);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    @Override
    public boolean removeProduct(Cart cart, Product product) {

        int totalAmount = (int) (cart.getTotalAmount() - product.getItemCost());

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateCurrent = new Date();

        String dateDB = dateFormat.format(dateCurrent);

        String query = "ALTER TABLE `Cart` DROP (`Product`) WHERE idCart=? AND Product=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1,cart.getIdCart());
            ps.setString(2,product.getName());

            // exécution de la requête
            ps.executeQuery();

            // update du montant du panier
            cart.setTotalAmount(totalAmount);


            query = "INSERT INTO `Cart` (`DateOfChange`, `TotalAmount`) VALUES (?, ?) WHERE idCart=?";

            // construction et exécution d'une requête préparée
            try ( PreparedStatement ps2 = dbConnection.prepareStatement(query) ){
                ps2.setString(1,dateDB);
                ps2.setInt(2,totalAmount);
                ps2.setInt(3, cart.getIdCart());

                // exécution de la requête
                ps2.executeQuery();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
