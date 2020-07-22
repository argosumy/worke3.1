package project.entities;

import project.dao.DaoEntitiesMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.SQLException;

public class Product extends Entity implements DaoEntitiesMethod {
    private String description;
    private float price;
    private boolean isActive;
    private Categories categories;
    private static final Logger LOGGER = Logger.getLogger(Categories.class);

    public Product() {
        super();
    }

    public Product(String name, String description, float price, boolean isActive, Categories categories) {
        super(name);
        this.description = description;
        this.price = price;
        this.isActive = isActive;
        this.categories = categories;
    }

    @Override
    public void addEntity(Connection con) throws SQLException {

    }

    @Override
    public void upDateEntity(int id) {

    }

    @Override
    public void deleteEntity(Connection con,int id) {

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
}
