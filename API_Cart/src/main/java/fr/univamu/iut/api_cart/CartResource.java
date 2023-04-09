package fr.univamu.iut.api_cart;

import fr.univamu.iut.api_cart.UserNProduct.Product;
import fr.univamu.iut.api_cart.UserNProduct.UsAndProDBInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Response;

/**
 * Classe permettant d'accéder au différentes fonctionnalités de l'api via l'url
 */

@Path("/carts")
@ApplicationScoped
public class CartResource {
    /**
     * Service utilisé pour accéder aux données des livres et récupérer/modifier leurs informations
     */
    private CartService service;

    public CartResource(){}
    
    public @Inject CartResource(CartInterfaceDB cartRepo, UsAndProDBInterface usAndProRepo){
        this.service = new CartService( cartRepo, usAndProRepo) ;
    }

    public CartResource(CartService service ){
        this.service = service;
    }

    /**
     * Enpoint permettant de publier tous les paniers enregistrés
     * @return la liste des paniers (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllCarts() {
        return service.getAllCartsJSON();
    }

    /**
     * Enpoint permettant de publier un panier en particulier
     * @return le panier souhaité (avec ses informations) au format JSON
     */
    @GET
    @Path("{idCart}")
    @Produces("application/json")
    public String getCart( @PathParam("idCart") int idCart){

        String result = service.getCartJSON(idCart);

        // si le Panier n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    /**
     * Enpoint permettant de créer un panier
     * @return une réponse en fonction de la réussite de l'Enpoint
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response createCart( @FormParam("mail") String mail){
        if( service.createCart(mail))
            return Response.ok("create").build();
        else
            return Response.status( Response.Status.CONFLICT ).build();

    }

    /**
     * Enpoint permettant de supprimer un panier
     * @return une réponse en fonction de la réussite de l'Enpoint
     */
    @DELETE
    @Path("{idCart}")
    public Response deleteCart(@PathParam("idCart") int idCart){

        if( service.deleteCart(idCart) )
            return Response.ok("removed").build();
        else
            return Response.status( Response.Status.NOT_FOUND ).build();
    }

    /**
     * Enpoint permettant de vérifier la mise à jour d'un panier
     * @return une réponse en fonction de la réussite de l'Enpoint
     */
    @PUT
    @Path("{idCart}")
    @Consumes("application/json")
    public Response updateCart(@PathParam("idCart") int idCart, Product product, Cart cart){

        // si le livre n'a pas été trouvé
        if(!service.addProduct(cart, product) ||  !service.removeProduct(cart, product))
            throw new NotFoundException("Cart not updated yet");
        else
            return Response.ok("updated").build();
    }
}