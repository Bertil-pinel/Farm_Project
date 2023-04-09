package fr.univamu.fr.api_userproduct;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Classe permettant d'accéder au différentes fonctionnalités de l'api via l'url
 */

@Path("/users")
@ApplicationScoped
public class UserResource {

    /**
     * Service utilisé pour accéder aux données des utilisateurs et récupérer/modifier leurs informations
     */
    private UserService service;

    /**
     * Constructeur par défaut
     */
    public UserResource(){}

    /**
     * Constructeur par injection via l'URL
     * @param userDB
     */
    public @Inject UserResource( UsAndProDBInterface userDB ){
        this.service = new UserService(userDB) ;
    }

    /**
     * Constructeur
     * @param service
     */

    public UserResource( UserService service ){
        this.service = service;
    }

    /**
     * Endpoint permettant de récupérer tous les Utilisateurs
     * @return la liste des utilisateurs au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsersJSON();
    }

    /**
     * Endpoint permettant de récupérer l'utilisateurs dont le mail est spécifié dans l'url (mail)
     * @return L'utilisateur au format JSON
     */

    @GET
    @Path("{mail}")
    @Produces("application/json")
    public String getUser( @PathParam("mail") String mail){

        String result = service.getUserJSON(mail);


        if( result == null )
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de créer Un utilisateur
     * @param idCart référence de du panier à utilisé
     * @param relayPlace adresse du relai de livraison
     * @param orderDate date de la commande
     * @return La réponse à la réussite de la fonction
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response registerOrder(@FormParam("username") String username, @FormParam("mail") String mail, @FormParam("password") String password){
        if( service.createUser(username,mail, password))
            return Response.ok("registred").build();
        else
            return Response.status( Response.Status.CONFLICT ).build();

    }

}