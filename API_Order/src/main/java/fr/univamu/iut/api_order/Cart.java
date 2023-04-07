package fr.univamu.iut.api_order;

/**
 * Classe représantant une Commande
 */
public class Cart {

    /**
     * Id du panier
     */
    protected int idCart;

    /**
     * Prix total
     */
    protected int amountPrice;

    /**
     * Date de dernière modification
     */
    protected String modifDate;

    /**
     * Id des produits
     */
    protected String idProduct;

    /**
     * Id utilsateur
     */
    protected String idUser;

    /**
     * Constructeur par défaut
     */
    public Cart(){}

    /**
     * Constructeur de la classe panier
     * @param idCart
     * @param amountPrice
     * @param modifDate
     * @param idProduct
     * @param idUser
     */
    public Cart(int idCart, int amountPrice, String modifDate, String idProduct, String idUser) {
        this.idCart = idCart;
        this.amountPrice = amountPrice;
        this.modifDate = modifDate;
        this.idProduct = idProduct;
        this.idUser = idUser;
    }

    /**
     * Modifie l'Id des produits
     * @param idProduct
     */
    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    /**
     * Modifie l'Id de l'utilisateur
     * @param idUser
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * Modifie l'Id du panier
     * @param idCart
     */
    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    /**
     * Modifie le prix total
     * @param amountPrice
     */
    public void setAmountPrice(int amountPrice) {
        this.amountPrice = amountPrice;
    }

    /**
     * Modifie la dernière date de modification
     * @param modifDate
     */
    public void setModifDate(String modifDate) {
        this.modifDate = modifDate;
    }

    /**
     * Récupère l'Id du panier
     * @return int
     */
    public int getIdCart() {
        return idCart;
    }

    /**
     * Récupère le prix total du panier
     * @return int
     */
    public int getAmountPrice() {
        return amountPrice;
    }

    /**
     * Récupère la date de dernière modification du panier
     * @return String
     */
    public String getModifDate() {
        return modifDate;
    }

    /**
     * Récupère les Id des produits du panier
     * @return String
     */
    public String getIdProduct() {
        return idProduct;
    }

    /**
     * Récupère l'Id de l'utilisateur du panier
     * @return String
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * Renvoie sous forme d'un string toutes les informations d'un panier
     * @return String
     */
    @Override
    public String toString() {
        return "Cart{" +
                "idCart=" + idCart +
                ", amountPrice=" + amountPrice +
                ", modifDate='" + modifDate + '\'' +
                ", idProduct='" + idProduct + '\'' +
                ", idUser='" + idUser + '\'' +
                '}';
    }
}