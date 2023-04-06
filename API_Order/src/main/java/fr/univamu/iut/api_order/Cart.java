package fr.univamu.iut.api_order;

/**
 * Classe représantant une Commande
 */
public class Cart {

    /**
     * Id de la commande
     */
    protected int idCart;

    /**
     * Localisation point de relais
     */
    protected int amountItem;

    /**
     * Date de retrait
     */
    protected String modifDate;

    /**
     * Référence de la commande
     */
    protected String idProduct;

    /**
     * Prix de la commande
     */
    protected String idUser;

    /**
     *
     */
    public Cart(){}

    public Cart(int idCart, int amountItem, String modifDate, String idProduct, String idUser) {
        this.idCart = idCart;
        this.amountItem = amountItem;
        this.modifDate = modifDate;
        this.idProduct = idProduct;
        this.idUser = idUser;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public void setAmountItem(int amountItem) {
        this.amountItem = amountItem;
    }

    public void setModifDate(String modifDate) {
        this.modifDate = modifDate;
    }

    public int getIdCart() {
        return idCart;
    }

    public int getAmountItem() {
        return amountItem;
    }

    public String getModifDate() {
        return modifDate;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public String getIdUser() {
        return idUser;
    }
}