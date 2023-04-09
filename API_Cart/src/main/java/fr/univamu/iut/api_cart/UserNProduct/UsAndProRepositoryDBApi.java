package fr.univamu.iut.api_cart.UserNProduct;;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.Closeable;
import java.util.ArrayList;

public class UsAndProRepositoryDBApi implements UsAndProDBInterface, Closeable {

    /**
     * URL de l'API des User & Products
     */
    String url;


    /**
     * Constructor
     * @param infoConnection information needed to connect to mysql
     * @param user Identifier to connect to mysql
     * @param pwd password to connect to mysql
     */
    public UsAndProRepositoryDBApi(String url){
        this.url = url;
    }

    @Override
    public void close() {}

    @Override
    public Product getProduct(String idProduct) {

        Product myProduct = null;

        // création d'un client
        Client client = ClientBuilder.newClient();

        // définition de l'adresse de la ressource
        WebTarget productResource  = client.target(url);

        // définition du point d'accès
        WebTarget productEndpoint = productResource.path("products/" + idProduct);

        // envoi de la requête et récupération de la réponse
        Response response = productEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si le produit a bien été trouvé, conversion du JSON en Product
        if( response.getStatus() == 200)
            myProduct = response.readEntity(Product.class);

        // fermeture de la connexion
        client.close();
        return myProduct;

    }

    @Override
    public ArrayList<Product> getAllProducts() {

        ArrayList<Product> myProducts = new ArrayList<>();

        // création d'un client
        Client client = ClientBuilder.newClient();

        // définition de l'adresse de la ressource
        WebTarget productsResource = client.target(url);

        // définition du point d'accès
        WebTarget productsEndpoint = productsResource.path("products/");

        // envoi de la requête et récupération de la réponse
        Response response = productsEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si les produits ont bien été trouvé, conversion du JSON en Products
        if (response.getStatus() == 200) {
            myProducts = response.readEntity(ArrayList.class);
        }

        // fermeture de la connexion
        client.close();
        return myProducts;
    }


    @Override
    public User getUser(String Mail) {

        User myUser = null;

        // création d'un client
        Client client = ClientBuilder.newClient();

        // définition de l'adresse de la ressource
        WebTarget userResource = client.target(url);

        // définition du point d'accès
        WebTarget userEndpoint = userResource.path("users/" + Mail);

        // envoi de la requête et récupération de la réponse
        Response response = userEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si l'utilisateur a bien été trouvé, conversion du JSON en User
        if (response.getStatus() == 200) {
            myUser = response.readEntity(User.class);
        }

        // fermeture de la connexion
        client.close();
        return myUser;
    }

    @Override
    public ArrayList<User> getAllUsers() {

        ArrayList<User> myUsers = new ArrayList<>();

        // création d'un client
        Client client = ClientBuilder.newClient();

        // définition de l'adresse de la ressource
        WebTarget usersResource = client.target(url);

        // définition du point d'accès
        WebTarget usersEndpoint = usersResource.path("users/");

        // envoi de la requête et récupération de la réponse
        Response response = usersEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si les utilisateurs ont bien été trouvé, conversion du JSON en Users
        if (response.getStatus() == 200) {
            myUsers = response.readEntity(ArrayList.class);
        }

        // fermeture de la connexion
        client.close();
        return myUsers;
    }
}
