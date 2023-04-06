package fr.univamu.iut.api_order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Classe permettant d'accéder au différentes fonctionnalités de l'api via l'url
 */
@Path("/orders")
@ApplicationScoped
public class OrderResource {
    /**
     * Service utilisé pour accéder aux données des commandes et récupérer/modifier leurs informations
     */
    private OrderService service;

    /**
     * constructeur par défaut
     */
    public OrderResource(){}

    /**
     * constructeur injectant pour le fonctionnement dans l'URL
     * @param orderRepo
     * @param cartRepo
     */
    public @Inject OrderResource(OrderInterfaceDB orderRepo, CartInterfaceDB cartRepo){
        this.service = new OrderService( orderRepo , cartRepo ) ;
    }

    /**
     * constructeur
     * @param service
     */
    public OrderResource( OrderService service ){
        this.service = service;
    }

    /**
     * Enpoint permettant de récupérer toutes les Commandes enregistrés
     * @return la liste des commandes (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllOrders() {
        return service.getAllOrdersJSON();
    }

    /**
     * Endpoint permettant de récupérer les informations d'une commande dont la référence est passée en paramètre dans le chemin
     * @param idOrder référence de la commande recherchée
     * @return les informations de la commande recherchée au format JSON
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

    /**
     * Endpoint permettant de supprimer une commande dont la référence est passée en paramètre dans le chemin
     * @param idOrder référence de la commande recherchée
     * @return La réponse à la réussite de la fonction
     */
    @DELETE
    @Path("{idOrder}")
    public Response removeOrder(@PathParam("idOrder") int idOrder){

        if( service.deleteOrder(idOrder) )
            return Response.ok("removed").build();
        else
            return Response.status( Response.Status.NOT_FOUND ).build();
    }

    /**
     * Endpoint permettant de valider une commande dont la référence est passée en paramètre dans le chemin
     * @param idOrder référence de la commande recherchée
     * @return La réponse à la réussite de la fonction
     */
    @PUT
    @Consumes("application/x-www-form-urlencoded")
    public Response validateOrder( @FormParam("idOrder") int idOrder){

        if( service.validateOrder(idOrder) )
            return Response.ok("validated").build();
        else
            return Response.status( Response.Status.CONFLICT ).build();
    }


    /**
     * Endpoint permettant de créer une commande dont la référence du panier est passée en paramètre dans le chemin
     * @param idCart référence de du panier à utilisé
     * @param relayPlace adresse du relai de livraison
     * @param orderDate date de la commande
     * @return La réponse à la réussite de la fonction
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response registerOrder( @FormParam("idCart") int idCart, @FormParam("relayPlace") String relayPlace, @FormParam("orderDate") String orderDate){
        if( service.createOrder(idCart, relayPlace, orderDate))
            return Response.ok("registred").build();
        else
            return Response.status( Response.Status.CONFLICT ).build();

    }

}