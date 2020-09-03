package main.java.project.entities;

public class Product extends Entity {
    private String description;
    private float price;
    private int isActive;
    private int categoryId;
    private Categories categories;

    public Product() {
        super();
    }

    public Product(String name, String description, float price, int isActive,  int categoryId) {
        super(name);
        this.description = description;
        this.price = price;
        this.isActive = isActive;
        this.categoryId = categoryId;
    }

    public Product(int id, String name, String description, float price, int isActive, int categoryId) {
        super(id, name);
        this.description = description;
        this.price = price;
        this.isActive = isActive;
        this.categoryId = categoryId;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setActive(int active) {
        isActive = active;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

}
