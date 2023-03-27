package fr.univamu.fr.api_userproduct;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/users")
@ApplicationScoped
public class UserResource {

    private UserService service;

    public UserResource(){}

    public @Inject UserResource( UserDBInterface userDB ){
        this.service = new UserService(userDB) ;
    }


    public UserResource( UserService service ){
        this.service = service;
    }


    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsersJSON();
    }


    @GET
    @Path("{mail}")
    @Produces("application/json")
    public String getUser( @PathParam("mail") String mail){

        String result = service.getUserJSON(mail);


        if( result == null )
            throw new NotFoundException();

        return result;
    }

}