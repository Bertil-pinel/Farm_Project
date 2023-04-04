package fr.univamu.iut.api_order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/orders")
@ApplicationScoped
public class OrderResource {
    /**
     * Service utilisé pour accéder aux données des livres et récupérer/modifier leurs informations
     */
    private OrderService service;

    public OrderResource(){}

    public @Inject OrderResource(OrderInterfaceDB orderRepo, CartInterfaceDB cartRepo){
        this.service = new OrderService( orderRepo , cartRepo ) ;
    }

    public OrderResource( OrderService service ){
        this.service = service;
    }

    /**
     * Enpoint permettant de publier de tous les livres enregistrés
     * @return la liste des livres (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllOrders() {
        return service.getAllOrdersJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un livre dont la référence est passée paramètre dans le chemin
     * @param idOrder référence du livre recherché
     * @return les informations du livre recherché au format JSON
     */
    @GET
    @Path("{idOrder}")
    @Produces("application/json")
    public String getOrder( @PathParam("idOrder") int idOrder){

        String result = service.getOrderJSON(idOrder);

        // si le livre n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    @DELETE
    @Path("{idOrder}")
    public Response removeOrder(@PathParam("idOrder") int idOrder){

        if( service.deleteOrder(idOrder) )
            return Response.ok("removed").build();
        else
            return Response.status( Response.Status.NOT_FOUND ).build();
    }

    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public Response validateOrder( @FormParam("idOrder") int idOrder){

        if( service.validateOrder(idOrder) )
            return Response.ok("validated").build();
        else
            return Response.status( Response.Status.CONFLICT ).build();
    }


    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response registerOrder( @FormParam("idCart") int idCart, @FormParam("relayPlace") String relayPlace, @FormParam("orderDate") String orderDate){
        if( service.createOrder(idCart, relayPlace, orderDate))
            return Response.ok("registred").build();
        else
            return Response.status( Response.Status.CONFLICT ).build();

    }

}