package fr.univamu.iut.api_cart;

import java.util.Date;

/**
 * Classe représantant une Commande
 */
public class Cart {

    /**
     * Prix de la commande
     */
    protected String idUser;

    /**
     * Localisation point de relais
     */
    protected int amountItem;

    /**
     * Date de retrait
     */
    protected Date modifDate;

    /**
     * Référence de la commande
     */
    protected String idProduct;

    /**
     *
     */
    public Cart(){}

    public Cart(String idUser, int amountItem, Date modifDate, String idProduct) {
        this.idUser = idUser;
        this.amountItem = amountItem;
        this.modifDate = modifDate;
        this.idProduct = idProduct;
    }

    public String getIdUser() {
        return idUser;
    }

    public int getAmountItem() {
        return amountItem;
    }

    public Date getModifDate() {
        return modifDate;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setAmountItem(int amountItem) {
        this.amountItem = amountItem;
    }

    public void setModifDate(Date modifDate) {
        this.modifDate = modifDate;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idUser='" + idUser + '\'' +
                ", amountItem=" + amountItem +
                ", modifDate=" + modifDate +
                ", idProduct='" + idProduct + '\'' +
                '}';
    }
}