package fr.univamu.fr.api_userproduct;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

/**
 * Classe permettant d'accéder au différentes fonctionnalités de l'api via l'url
 */

@Path("/products")
@ApplicationScoped
public class ProductResource {

    /**
     * Service utilisé pour accéder aux données des produits et récupérer/modifier leurs informations
     */
    private UserService service;

    /**
     * Constructeur par défaut
     */

    public ProductResource(){}

    /**
     * Constructeur avec injection par l'URL
     * @param userDB
     */

    public @Inject ProductResource( UsAndProDBInterface userDB ){
        this.service = new UserService(userDB) ;
    }

    /**
     * Constructeur
     * @param service
     */

    public ProductResource( UserService service ){
        this.service = service;
    }

    /**
     * Endpoint permettant de récupérer tous les produits
     * @return la liste des produits au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllProducts() {
        return service.getAllProductsJSON();
    }

    /**
     * Endpoint permettant de récupérer le produit qui porte ce nom
     * @return le produit au format JSON
     */
    @GET
    @Path("{name}")
    @Produces("application/json")
    public String getProduct( @PathParam("name") String name){

        String result = service.getProdcutJSON(name);


        if( result == null )
            throw new NotFoundException();

        return result;
    }

}