package fr.univamu.iut.api_order;

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
    protected int orderPrice;

    /**
     * Localisation point de relais
     */
    protected String relayPlace;

    /**
     * Date de retrait
     */
    protected String orderDate;

    /**
     * Validation
     */
    protected boolean isValidate;

    /**
     * liste des produits
     */
    protected String recapOrder;

    /**
     * Constructeur par défaut
     */
    public Order(){}

    /**
     * Constructeur
     * @param idOrder
     * @param orderPrice
     * @param relayPlace
     * @param orderDate
     * @param isValidate
     * @param recapOrder
     */
    public Order(int idOrder, int orderPrice, String relayPlace, String orderDate, boolean isValidate, String recapOrder) {
        this.idOrder = idOrder;
        this.orderPrice = orderPrice;
        this.relayPlace = relayPlace;
        this.orderDate = orderDate;
        this.isValidate = isValidate;
        this.recapOrder = recapOrder;
    }

    /**
     * Modifie la validation du commande
     * @param validate
     */
    public void setValidate(boolean validate) {
        isValidate = validate;
    }

    /**
     * Vérifie si la commande est validée ou non
     * @return boolean
     */
    public boolean isValidate() {
        return isValidate;
    }

    /**
     * Récupère l'ID de la commande
     * @return int
     */
    public int getIdOrder() {
        return idOrder;
    }

    /**
     * Modifie la liste des produits de la commande
     * @param recapOrder
     */
    public void setRecapOrder(String recapOrder) {
        this.recapOrder = recapOrder;
    }

    /**
     * Récupère la liste des produits de la commande
     * @return String
     */
    public String getRecapOrder() {
        return recapOrder;
    }

    /**
     * Récupère le prix total de la commande
     * @return float
     */
    public int getOrderPrice() {
        return orderPrice;
    }

    /**
     * Récupère le relai de livraison de la commande
     * @return String
     */
    public String getRelayPlace() {
        return relayPlace;
    }

    /**
     * Récupère la date de la commande
     * @return String
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * Modifie l'Id de la commande
     * @param idOrder
     */
    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    /**
     * Modifie le prix total de la commande
     * @param orderPrice
     */
    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * Modifie le relai de livraison de la commande
     * @param relayPlace
     */
    public void setRelayPlace(String relayPlace) {
        this.relayPlace = relayPlace;
    }

    /**
     * Modifie la date de la commande
     * @param orderDate
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Méthode toString
     * @return String les informations d'une commande
     */
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
