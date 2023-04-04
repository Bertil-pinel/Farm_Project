package fr.univamu.iut.api_order;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'éccès aux données)
 */
public class OrderService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les livres
     */
    protected OrderInterfaceDB orderRepo ;
    protected CartInterfaceDB cartRepo;

    /*public OrderService(OrderInterfaceDB orderRepo) {
        this.orderRepo = orderRepo;
    }*/

    public OrderService(OrderInterfaceDB orderRepo, CartInterfaceDB cartRepo) {
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
    }

    /**
     * Méthode retournant les informations sur les livres au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllOrdersJSON(){

        ArrayList<Order> allOrders = orderRepo.getAllOrder();

        // création du json et conversion de la liste de livres
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allOrders);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un livre recherché
     * @param idOrder la référence du livre recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getOrderJSON( int idOrder ){
        String result = null;
        Order myOrder = orderRepo.getOrder(idOrder);

        // si le livre a été trouvé
        if( myOrder != null ) {

            // création du json et conversion du livre
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myOrder);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    public boolean createOrder(int idCart, String relayPlace, String orderDate){
        boolean result = false;

        if(cartRepo.getCart(idCart) == null ){
            throw new NotFoundException("Cart not exists");
        }else{
            result = orderRepo.createOrder(cartRepo.getCart(idCart).getAmountItem(),relayPlace, orderDate,false,cartRepo.getCart(idCart).getIdProduct());
        }
        return result;
    }

    public boolean validateOrder(int idOrder){
        boolean result = orderRepo.validateOrder(idOrder);
        return result;
    }

    public boolean deleteOrder(int idOrder){
        boolean result = orderRepo.deleteOrder(idOrder);
        return result;
    }

}

