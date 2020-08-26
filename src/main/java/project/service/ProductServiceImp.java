package project.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import project.dao.ConstSQLTable;
import project.entities.Categories;
import project.entities.Entity;
import project.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImp implements EntityService {
    private Product product;
    private static final Logger LOGGER = Logger.getLogger(ProductServiceImp.class);

    public ProductServiceImp() {
    }

    public ProductServiceImp(Product product) {
        this.product = product;
    }

    @Override
    public void addEntity(Connection con) {
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.ADD_PRODUCT);
            prStatement.setString(1,product.getName());
            prStatement.setString(2,product.getDescription());
            prStatement.setFloat(3,product.getPrice());
            prStatement.setInt(4,product.getIsActive());
            prStatement.setInt(5,product.getCategoryId());
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method insert Entity in ProductService",e);
        }

    }

    @Override
    public void upDateEntity(Connection con, int id) {
//"UPDATE BOOK_PRODUCT SET NAME = ?,DESCRIPTION = ?,PRICE = ?,IS_ACTIVE = ?,CATEGORY_ID = ? WHERE PRODUCT_ID = ?"
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.UPDATE_PRODUCT_ID);
            prStatement.setString(1,product.getName());
            prStatement.setString(2,product.getDescription());
            prStatement.setFloat(3,product.getPrice());
            prStatement.setInt(4,product.getIsActive());
            prStatement.setInt(5,product.getCategoryId());
            prStatement.setInt(6,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method delete Entity in ProductService",e);
        }

    }

    @Override
    public void deleteEntity(Connection con, int id) {
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.DELETE_PRODUCT_ID);
            prStatement.setInt(1,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method delete Entity in Product",e);
        }
    }

    @Override
    public List<Entity> showAllEntity(Connection con) {
        List<Entity> productList = new ArrayList<>();
        Categories category;
        CategoriesServiceImp categoriesService = new CategoriesServiceImp();
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(ConstSQLTable.SHOW_ALL_PRODUCTS);
            while (resultSet.next()){
                int id = resultSet.getInt("PRODUCT_ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                float price = resultSet.getFloat("PRICE");
                int isActive = resultSet.getInt("IS_ACTIVE");
                int categoryId = resultSet.getInt("CATEGORY_ID");
                Product product = new Product(id,name,description,price,isActive,categoryId);
                //category = new Categories();
                category =(Categories) categoriesService.getEntityID(con,categoryId);
                product.setCategories(category);
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
            LOGGER.error("ERROR method showEntityID in ProductsService",e);
        }
        return product ;
    }

    @Override
    public List<Entity> showEntityByParentId(Connection con, int parentID) {
        Product product;
        List <Entity> productList = new ArrayList<>();
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.SHOW_PRODUCTS_BY_CATEGORY_ID);
            prStatement.setInt(1,parentID);
            ResultSet resultSet = prStatement.executeQuery();
            while (resultSet.next()){
                int idProduct = resultSet.getInt("PRODUCT_ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                float price = resultSet.getFloat("PRICE");
                int isActive = resultSet.getInt("IS_ACTIVE");
                int categoryId = resultSet.getInt("CATEGORY_ID");
                product = new Product(idProduct,name,description,price,isActive,categoryId);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
