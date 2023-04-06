package fr.univamu.iut.api_order;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe des requêtes à la base de données des commandes
 */
public class OrderDB implements OrderInterfaceDB{
    /**
     * Connexion à la base de données
     */
    protected Connection dbConnection ;

    /**
     * Constructeur initialisant la connexion à la base de données
     * @param infoConnection
     * @param user
     * @param pwd
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public OrderDB(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    /**
     * fermeture de la connexion
     */
    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * Récupère une commande selon son ID de la base de données
     * @return une commande
     */
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
                int orderPrice = result.getInt("orderPrice");
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

    /**
     * Récupère toutes les commandes de la base de données
     * @return ArrayList de commande
     */
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
                int orderPrice = result.getInt("orderPrice");
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

    /**
     * Créer une commande dans la base de données
     * @param orderPrice
     * @param relayPlace
     * @param orderDate
     * @param isValidate
     * @param recapOrder
     * @return boolean oui si la requête à fonctionner non dans le cas contraire
     */
    @Override
    public boolean createOrder(int orderPrice, String relayPlace, String orderDate, boolean isValidate, String recapOrder) {

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

    /**
     * Supprime une commande de la base de données
     * @param idOrder
     * @return boolean oui si la requête à fonctionner non dans le cas contraire
     */
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

    /**
     * Valide une commande de la base de données
     * @param idOrder
     * @return boolean oui si la requête à fonctionner non dans le cas contraire
     */
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
