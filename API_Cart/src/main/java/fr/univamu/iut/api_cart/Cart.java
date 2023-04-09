package fr.univamu.iut.api_cart;

/**
 * Classe représantant un panier
 */
public class Cart {


    /**
     * Id du panier
     */
    protected int idCart;

    /**
     * Somme total du panier
     */
    protected int totalAmount;

    /**
     * Date de modification du panier
     */
    protected String dateOfChange;

    /**
     * Id d'un produit du panier
     */
    protected String idProduct;

    /**
     * Id de l'utilisateur créant le panier
     */
    protected String idUser;

    public Cart(int idCart, int totalAmount, String dateOfChange, String idProduct, String idUser) {
        this.idCart = idCart;
        this.totalAmount = totalAmount;
        this.dateOfChange = dateOfChange;
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

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDateOfChange(String dateOfChange) {
        this.dateOfChange = dateOfChange;
    }

    public int getIdCart() {
        return idCart;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getDateOfChange() {
        return dateOfChange;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public String getIdUser() {
        return idUser;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idCart=" + idCart +
                ", totalAmount=" + totalAmount +
                ", dateOfChange='" + dateOfChange + '\'' +
                ", idProduct='" + idProduct + '\'' +
                ", idUser='" + idUser + '\'' +
                '}';
    }
}