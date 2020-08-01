package project.entities;

import project.dao.ConstSQLTable;
import project.dao.DaoEntitiesMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product extends Entity implements DaoEntitiesMethod {
    private String description;
    private float price;
    private int isActive;
    private int categoryId;
    private Categories categories;
    private static final Logger LOGGER = Logger.getLogger(Categories.class);

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

    @Override
    public void addEntity(Connection con){
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.ADD_PRODUCT);
            prStatement.setString(1,this.getName());
            prStatement.setString(2,description);
            prStatement.setFloat(3,price);
            prStatement.setInt(4,isActive);
            prStatement.setInt(5,categoryId);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method insert Entiti in Product",e);
            System.out.println(e);
        }
        catch (Exception e){
            LOGGER.error("ERROR method insert Entiti in Product",e);
            System.out.println(e);
        }
    }

    @Override
    public void upDateEntity(Connection con,int id) {

    }

    @Override
    public void deleteEntity(Connection con,int id) {
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.DELETE_PRODUCT_ID);
            prStatement.setInt(1,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method delete Entiti in Product",e);
        }
    }

    @Override
    public List<Entity> showAllEntity(Connection con) {
        List productList = new ArrayList<Product>();
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(ConstSQLTable.SHOW_ALL_PRODUCTS);
            while (resultSet.next()){
                int id = resultSet.getInt("PRODUCT_ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                Float price = resultSet.getFloat("PRICE");
                int isActive = resultSet.getInt("IS_ACTIVE");
                int categoryId = resultSet.getInt("CATEGORY_ID");
                Product product = new Product(id,name,description,price,isActive,categoryId);
                System.out.println(product.toString());
                productList.add(product);
            }
        }
        catch (SQLException e){
            LOGGER.error("ERROR method showAllEntity in Product",e);
        }
        return productList;
    }


    @Override
    public Entity getEntityID(Connection con, int id) {
        Entity product = null;
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.SHOW_PRODUCT_ID);
            prStatement.setInt(1,id);
            ResultSet resultSet = prStatement.executeQuery();
            resultSet.next();
            int idProduct = resultSet.getInt("PRODUCT_ID");
            String name = resultSet.getString("NAME");
            String description = resultSet.getString("DESCRIPTION");
            float price = resultSet.getFloat("PRICE");
            int isActive = resultSet.getInt("IS_ACTIVE");
            int categoryId = resultSet.getInt("CATEGORY_ID");
            product = new Product(idProduct,name,description,price,isActive,categoryId);
        }
        catch (SQLException e){
            LOGGER.error("ERROR method showEntityID in Products",e);
        }
        return product ;
    }

    @Override
    public List<Entity> showEntityByParentId(Connection connection) {
        return null;
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

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }
}
