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
    protected String orderDate;

    /**
     *
     */
    protected boolean isValidate;

    /**
     *
     */
    protected String recapOrder;

    /**
     *
     */
    public Order(){}


    public Order(int idOrder, float orderPrice, String relayPlace, String orderDate, boolean isValidate, String recapOrder) {
        this.idOrder = idOrder;
        this.orderPrice = orderPrice;
        this.relayPlace = relayPlace;
        this.orderDate = orderDate;
        this.isValidate = isValidate;
        this.recapOrder = recapOrder;
    }

    public void setValidate(boolean validate) {
        isValidate = validate;
    }

    public boolean isValidate() {
        return isValidate;
    }

    /**
     *
     * @return
     */
    public int getIdOrder() {
        return idOrder;
    }

    public void setRecapOrder(String recapOrder) {
        this.recapOrder = recapOrder;
    }

    public String getRecapOrder() {
        return recapOrder;
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
    public String getOrderDate() {
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
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", orderPrice=" + orderPrice +
                ", relayPlace='" + relayPlace + '\'' +
                ", orderDate=" + orderDate +
                ", isValidate=" + isValidate +
                '}';
    }
}
