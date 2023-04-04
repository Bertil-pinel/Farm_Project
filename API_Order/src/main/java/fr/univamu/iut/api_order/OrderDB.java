package fr.univamu.iut.api_order;

import java.sql.*;
import java.util.ArrayList;

public class OrderDB implements OrderInterfaceDB{

    protected Connection dbConnection ;

    public OrderDB(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
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
    public Order getOrder(int idOrder) {
        Order selectedOrder = null;

        String query = "SELECT * FROM `Order` WHERE idOrder=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, idOrder);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du livre est valide)
            if( result.next() )
            {
                float orderPrice = result.getFloat("orderPrice");
                String relayPlace = result.getString("relayPlace");
                String orderDate = result.getString("orderDate");
                boolean isValidate = result.getBoolean("isValidate");
                String recapOrder = result.getString("recapOrder");

                // création et initialisation de l'objet Book
                selectedOrder = new Order(idOrder, orderPrice, relayPlace, orderDate, isValidate, recapOrder);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedOrder;
    }

    @Override
    public ArrayList<Order> getAllOrder() {
        ArrayList<Order> listOrders ;

        String query = "SELECT * FROM `Order`";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listOrders = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                int idOrder = result.getInt("idOrder");
                float orderPrice = result.getFloat("orderPrice");
                String relayPlace = result.getString("relayPlace");
                String orderDate = result.getString("orderDate");
                boolean isValidate = result.getBoolean("isValidate");
                String recapOrder = result.getString("recapOrder");

                // création du livre courant
                Order currentOrder = new Order(idOrder, orderPrice, relayPlace, orderDate, isValidate, recapOrder);

                listOrders.add(currentOrder);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOrders;
    }

    @Override
    public boolean createOrder(float orderPrice, String relayPlace, String orderDate, boolean isValidate, String recapOrder) {

        String query = "INSERT INTO `Order` (`idOrder`, `orderPrice`, `relayPlace`, `orderDate`, `isValidate`, `recapOrder`) VALUES (?,?,?,?,?,?)";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1,null);
            ps.setFloat(2,orderPrice);
            ps.setString(3,relayPlace);
            ps.setString(4,orderDate);
            ps.setBoolean(5,isValidate);
            ps.setString(6,recapOrder);

            // exécution de la requête
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean deleteOrder(int idOrder) {
        String query = "DELETE FROM `Order` WHERE idOrder=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ps.setInt(1, idOrder);
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean validateOrder(int idOrder) {
        String query = "UPDATE `Order` SET `isValidate`=1 WHERE idOrder=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ps.setInt(1, idOrder);
            ps.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;

    }

}
