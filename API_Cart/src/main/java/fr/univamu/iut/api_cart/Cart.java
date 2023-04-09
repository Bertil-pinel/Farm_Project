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

    /**
     * Constructeur du panier
     * @param idCart id du panier
     * @param totalAmount prix total du panier
     * @param dateOfChange date d'une modification du panier
     * @param idProduct id d'un produit du panier
     * @param idUser id de l'utilisateur créant le panier
     */
    public Cart(int idCart, int totalAmount, String dateOfChange, String idProduct, String idUser) {
        this.idCart = idCart;
        this.totalAmount = totalAmount;
        this.dateOfChange = dateOfChange;
        this.idProduct = idProduct;
        this.idUser = idUser;
    }

    /**
     * Méthode permettant de modifier l'id d'un produit
     * @param idProduct un String étant le nom du produit
     */
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * Méthode permettant d'insérer la dernière date de modification au panier'
     * @param dateOfChange un String étant la date du jour de la modification
     */
    public void setDateOfChange(String dateOfChange) {
        this.dateOfChange = dateOfChange;
    }

    /**
     * Méthode permettant d'insérer un nouveau product au panier
     * @param idProduct String visant le produit souhaité
     */
    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Méthode permettant d'accéder à l'id du panier
     * @return l'id du panier
     */
    public int getIdCart() {
        return idCart;
    }

    /**
     * Méthode permettant d'accéder à la somme totale du panier
     * @return la somme totale du panier
     */
    public int getTotalAmount() {
        return totalAmount;
    }

    /**
     * Méthode permettant d'accéder à la dernière date de modification du panier
     * @return la date de la modification
     */
    public String getDateOfChange() {
        return dateOfChange;
    }

    /**
     * Méthode permettant d'accéder à la référence de l'id d'un produit
     * @return l'id du produit
     */
    public String getIdProduct() {
        return idProduct;
    }

    /**
     * Méthode permettant d'accéder à l'id de l'utilisateur possédant le panier
     * @return le mail de l'utilisateur
     */
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