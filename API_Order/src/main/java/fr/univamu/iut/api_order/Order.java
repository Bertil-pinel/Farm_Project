package fr.univamu.iut.api_order;

import java.util.Date;

/**
 * Classe représantant une Commande
 */
public class Order {

    /**
     * Référence de la commande
     */
    protected int idOrder;

    /**
     * Prix de la commande
     */
    protected float orderPrice;

    /**
     * Localisation point de relais
     */
    protected String relayPlace;

    /**
     * Date de retrait
     */
    protected Date orderDate;

    /**
     *
     */
    public Order(){}

    /**
     *
     * @param id_Order
     * @param orderPrice
     * @param relayPlace
     * @param orderDate
     */
    public Order(int idOrder, float orderPrice, String relayPlace, Date orderDate) {
        this.idOrder = idOrder;
        this.orderPrice = orderPrice;
        this.relayPlace = relayPlace;
        this.orderDate = orderDate;
    }

    /**
     *
     * @return
     */
    public int getIdOrder() {
        return idOrder;
    }

    /**
     *
     * @return
     */
    public float getOrderPrice() {
        return orderPrice;
    }

    /**
     *
     * @return
     */
    public String getRelayPlace() {
        return relayPlace;
    }

    /**
     *
     * @return
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     *
     * @param idOrder
     */
    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    /**
     *
     * @param orderPrice
     */
    public void setOrderPrice(float orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     *
     * @param relayPlace
     */
    public void setRelayPlace(String relayPlace) {
        this.relayPlace = relayPlace;
    }

    /**
     *
     * @param orderDate
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Order{" +
                "id_Order=" + idOrder +
                ", orderPrice=" + orderPrice +
                ", relayPlace='" + relayPlace + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
