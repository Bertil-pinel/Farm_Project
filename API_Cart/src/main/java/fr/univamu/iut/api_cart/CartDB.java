package fr.univamu.iut.api_cart;

import fr.univamu.iut.api_cart.UserNProduct.Product;

import java.sql.*;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Classe intéragissant vec la BDD Cart
 *
 */

public class CartDB implements CartInterfaceDB{

    /**
     * Objet servant à la connexion à la BDD
     */
    protected Connection dbConnection ;

    /**
     * Constructeur de la connexion à la BDD
     * @param infoConnection String pour le nom de la BDD
     * @param user String pour le nom de l'utilisateur relié à la BDD
     * @param pwd String pour le mot de passe de la BDD
     */
    public CartDB(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    /**
     * Méthode qui ferme la connexion à la BDD
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
     * Méthode permettant d'accéder dans la BDD à un panier en particulier
     * @param idCard Int définissant le panier souhaité
     * @return le panier correspondans à idCart dans la BDD
     */
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

    /**
     * Méthode permettant d'accéder dans la BDD à tous les paniers
     * @return une liste de tous les paniers stockés dans la BDD
     */
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

    /**
     * Méthode permettant de créer un panier et le stocker dans la BDD
     * @param mail String pour l'ID de l'utilisateur créant le panier
     * @return un boolean pour connaitre la réussite de la méthode
     */
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

    /**
     * Méthode permettant de supprimer un panier
     * @param idCart int pour séléctionner le panier souhaité
     * @return un boolean pour connaitre la réussite de la méthode
     */
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

    /**
     * Méthode permettant d'ajouter un produit dans le panier souhaité
     * @param cart Cart visant le panier souhaité
     * @param product Product visant le produit souhaité
     * @return un boolean pour connaitre la réussite de la méthode
     */
    @Override
    public boolean addProduct(Cart cart, Product product) {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateCurrent = new Date();

        String dateDB = dateFormat.format(dateCurrent);

        cart.setDateOfChange(dateDB);

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

    /**
     * Méthode permettant d'de supprimer un produit dans le panier souhaité
     * @param cart Cart visant le panier souhaité
     * @param product Product visant le produit souhaité
     * @return un boolean pour connaitre la réussite de la méthode
     */
    @Override
    public boolean removeProduct(Cart cart, Product product) {

        int totalAmount = (int) (cart.getTotalAmount() - product.getItemCost());

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date dateCurrent = new Date();

        String dateDB = dateFormat.format(dateCurrent);

        cart.setDateOfChange(dateDB);

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
