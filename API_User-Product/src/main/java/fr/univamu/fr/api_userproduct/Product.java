package fr.univamu.fr.api_userproduct;

public class Product {

    protected String name;

    protected float costPerKilos;
    protected float itemCost;
    protected Category category;
    protected int disponibility;

    public Product(String name, float costPerKilos, float itemCost, Category category, int disponibility) {
        this.name = name;
        this.costPerKilos = costPerKilos;
        this.itemCost = itemCost;
        this.category = category;
        this.disponibility = disponibility;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCostPerKilos(float costPerKilos) {
        this.costPerKilos = costPerKilos;
    }

    public void setItemCost(float itemCost) {
        this.itemCost = itemCost;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDisponibility(int disponibility) {
        this.disponibility = disponibility;
    }

    public String getName() {
        return name;
    }

    public float getCostPerKilos() {
        return costPerKilos;
    }

    public float getItemCost() {
        return itemCost;
    }

    public Category getCategory() {
        return category;
    }

    public int getDisponibility() {
        return disponibility;
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
