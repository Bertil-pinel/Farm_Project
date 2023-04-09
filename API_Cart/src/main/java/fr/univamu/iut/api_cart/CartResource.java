package fr.univamu.iut.api_cart;

import fr.univamu.iut.api_cart.UserNProduct.Product;
import fr.univamu.iut.api_cart.UserNProduct.UsAndProDBInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Response;

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
     * Enpoint permettant de publier de tous les livres enregistrés
     * @return la liste des livres (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllCarts() {
        return service.getAllCartsJSON();
    }

    /**
     *
     * @param idCart
     * @return
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

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response createCart( @FormParam("mail") String mail){
        if( service.createCart(mail))
            return Response.ok("create").build();
        else
            return Response.status( Response.Status.CONFLICT ).build();

    }

    @DELETE
    @Path("{idCart}")
    public Response deleteCart(@PathParam("idCart") int idCart){

        if( service.deleteCart(idCart) )
            return Response.ok("removed").build();
        else
            return Response.status( Response.Status.NOT_FOUND ).build();
    }

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