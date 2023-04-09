package fr.univamu.iut.api_cart.UserNProduct;

/**
 * Classe représentant les produits
 */

public class Product {

    /**
     * Nom du produit
     */
    protected String name;

    /**
     * Le prix au kilos du produit
     */
    protected float costPerKilos;

    /**
     * Le prix du produit
     */
    protected float itemCost;

    /**
     * La catégorie du produit
     */
    protected Category category;

    /**
     * la disponibilité du produit (en stock ou non)
     */
    protected int disponibility;

    /**
     * Constructeur du produit
     * @param name String Nom du produit
     * @param costPerKilos prix au kilos du produit
     * @param itemCost prix du produit
     * @param category catégorie du produit
     * @param disponibility disponibilité du produit (en stock ou non)
     */
    public Product(String name, float costPerKilos, float itemCost, Category category, int disponibility) {
        this.name = name;
        this.costPerKilos = costPerKilos;
        this.itemCost = itemCost;
        this.category = category;
        this.disponibility = disponibility;
    }

    /**
     * Méthode permettant d'accéder au nom du produit
     * @return le nom du produit
     */
    public String getName() {
        return name;
    }

    /**
     * Méthode permettant d'accéder au prix du produit
     * @return le prix du produit
     */
    public float getItemCost() {
        return itemCost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", costPerKilos=" + costPerKilos +
                ", itemCost=" + itemCost +
                ", category=" + category +
                ", disponibility=" + disponibility +
                '}';
    }
}
