package fr.univamu.fr.api_userproduct;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

@Path("/products")
@ApplicationScoped
public class ProductResource {

    private UserService service;

    public ProductResource(){}

    public @Inject ProductResource( UsAndProDBInterface userDB ){
        this.service = new UserService(userDB) ;
    }


    public ProductResource( UserService service ){
        this.service = service;
    }

    @GET
    @Produces("application/json")
    public String getAllProducts() {
        return service.getAllProductsJSON();
    }


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