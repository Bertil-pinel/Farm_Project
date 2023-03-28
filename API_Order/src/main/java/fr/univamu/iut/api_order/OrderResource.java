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
    
    public @Inject OrderResource(OrderInterfaceDB orderRepo){
        this.service = new OrderService( orderRepo) ;
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
    public String getBook( @PathParam("idOrder") int idOrder){

        String result = service.getOrderJSON(idOrder);

        // si le livre n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    /**
     *
     * @param id
     * @return
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response registerOrder(@FormParam("id") int id){

        if( service.registerReservation(id))
            return Response.ok("registred").build();
        else
            return Response.status( Response.Status.CONFLICT ).build();
    }


}