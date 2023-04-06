package fr.univamu.iut.api_order;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

/**
 * Classe des requêtes à la base de données des paniers
 */
public class CartDBApi implements CartInterfaceDB {

    /**
     * URL de l'API des paniers
     */
    String url;

    /**
     * Constructeur initialisant l'url de l'API
     * @param url chaîne de caractères avec l'url de l'API
     */
    public CartDBApi(String url){
        this.url = url ;
    }

    /**
     * ferme la connexion à l'API
     */
    @Override
    public void close() {}

    /**
     * Méthode permettant de récupérer un panier selon sa référence
     * @param idCart
     * @return un panier
     */
    @Override
    public Cart getCart(int idCart) {
        Cart myCart = null;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget cartResource  = client.target(url);
        // définition du point d'accès
        WebTarget cartEndpoint = cartResource.path("carts/"+idCart);
        // envoi de la requête et récupération de la réponse
        Response response = cartEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si le Cart a bien été trouvé, conversion du JSON en Cart
        if( response.getStatus() == 200)
            myCart = response.readEntity(Cart.class);

        // fermeture de la connexion
        client.close();
        return myCart;
    }

    /**
     * Méthode permettant de récupérer tous les paniers
     * @return ArrayList de panier
     */
    @Override
    public ArrayList<Cart> getAllCart() {
        ArrayList<Cart> myCarts = new ArrayList<>();

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget cartResource  = client.target(url);
        // définition du point d'accès
        WebTarget cartEndpoint = cartResource.path("carts/");
        // envoi de la requête et récupération de la réponse
        Response response = cartEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si le livre a bien été trouvé, conversion du JSON en Carts
        if( response.getStatus() == 200){
            myCarts = response.readEntity(ArrayList.class);
        }

        // fermeture de la connexion
        client.close();
        return myCarts;
    }

}
