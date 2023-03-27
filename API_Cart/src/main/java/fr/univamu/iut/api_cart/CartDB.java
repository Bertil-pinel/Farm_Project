package fr.univamu.iut.api_cart;

import java.sql.*;
import java.util.ArrayList;
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
    public Cart getCart(String idUser, String idProduct) {
        Cart selectedCart = null;

        String query = "SELECT * FROM `Cart` WHERE idUser=? and idProduct=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
                ps.setString(1, idUser);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du livre est valide)
            if( result.next() )
            {
                int amountItem = result.getInt("amountItem");
                Date modifDate = result.getDate("modifDate");

                // création et initialisation de l'objet Book
                selectedCart = new Cart(idUser,amountItem,modifDate,idProduct);
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
                String idUser = result.getString("idUser");
                int amountItem = result.getInt("amountItem");
                Date modifDate = result.getDate("modifDate");
                String idProduct = result.getString("idProduct");


                // création du livre courant
                Cart currentOrder = new Cart(idUser, amountItem, modifDate, idProduct);

                listCarts.add(currentOrder);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listCarts;
    }
}
