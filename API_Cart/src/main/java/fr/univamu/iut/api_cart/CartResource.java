package fr.univamu.iut.api_cart;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/carts")
@ApplicationScoped
public class CartResource {
    /**
     * Service utilisé pour accéder aux données des livres et récupérer/modifier leurs informations
     */
    private CartService service;

    public CartResource(){}
    
    public @Inject CartResource(CartInterfaceDB cartRepo){
        this.service = new CartService( cartRepo) ;
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

}