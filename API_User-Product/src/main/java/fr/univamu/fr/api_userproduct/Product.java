package fr.univamu.fr.api_userproduct;

/**
 * Classe représentant un produit
 */

public class Product {

    /**
     * Nom du produit
     */
    protected String name;

    /**
     * Prix au kilo de ce produit
     */
    protected float costPerKilos;
    /**
     * Prix a l'unité de ce produit
     */
    protected float itemCost;

    /**
     * Catégorie du produit
     */
    protected Category category;
    /**
     * Combien d'item de ce produit son disponible a la vente
     */
    protected int disponibility;

    /**
     * Constructeur de la classe produit
     * @param name
     * @param costPerKilos
     * @param itemCost
     * @param category
     * @param disponibility
     */

    public Product(String name, float costPerKilos, float itemCost, Category category, int disponibility) {
        this.name = name;
        this.costPerKilos = costPerKilos;
        this.itemCost = itemCost;
        this.category = category;
        this.disponibility = disponibility;
    }


    /**
     * Modifie le nom du produit
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Modifie le prix au kilo du produit
     * @param costPerKilos
     */
    public void setCostPerKilos(float costPerKilos) {
        this.costPerKilos = costPerKilos;
    }

    /**
     * Modifie le prix a l'unité du produit
     * @param itemCost
     */
    public void setItemCost(float itemCost) {
        this.itemCost = itemCost;
    }

    /**
     * Modifie la catégorie du produit
     * @param category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Modifie le nombre d'item disponible du produit
     * @param disponibility
     */

    public void setDisponibility(int disponibility) {
        this.disponibility = disponibility;
    }

    /**
     * Récupère le nom du produit
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Récupère le prix au kilo du produit
     * @return float
     */

    public float getCostPerKilos() {
        return costPerKilos;
    }

    /**
     * Récupère le prix a l'unite du produit
     * @return float
     */
    public float getItemCost() {
        return itemCost;
    }

    /**
     * Récupère la catégorie à laquelle appartient le produit
     * @return Category
     */

    public Category getCategory() {
        return category;
    }

    /**
     * Récupère la disponibilté du produit
     * @return int
     */
    public int getDisponibility() {
        return disponibility;
    }

    /**
     * Renvoie sous forme de string la classe product
     * @return String
     */

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
