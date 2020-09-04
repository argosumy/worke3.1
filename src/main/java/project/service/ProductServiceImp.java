package main.java.project.service;

import main.java.project.dao.DaoConnection;
import main.java.project.entities.Categories;
import main.java.project.entities.Entity;
import main.java.project.entities.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.project.dao.ConstSQLTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductServiceImp implements EntityService {
    private Product product;
    private static final Logger LOGGER = Logger.getLogger(ProductServiceImp.class);
    private DaoConnection con;
    private CategoriesServiceImp categoriesService;

    @Autowired
    public ProductServiceImp(DaoConnection con, CategoriesServiceImp categoriesService) {
        this.con = con;
        this.categoriesService = categoriesService;
    }

    public ProductServiceImp(Product product) {
        this.product = product;
    }

    @Override
    public void addEntity() {
        Connection connection = con.connect();
        try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.ADD_PRODUCT);
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
        finally {
            con.disconnect();
        }
    }

    @Override
    public void upDateEntity(int id) {
//"UPDATE BOOK_PRODUCT SET NAME = ?,DESCRIPTION = ?,PRICE = ?,IS_ACTIVE = ?,CATEGORY_ID = ? WHERE PRODUCT_ID = ?"
        Connection connection = con.connect();
        try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.UPDATE_PRODUCT_ID);
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
        finally {
            con.disconnect();
        }
    }

    @Override
    public void deleteEntity(int id) {
        Connection connection = con.connect();
        try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.DELETE_PRODUCT_ID);
            prStatement.setInt(1,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method delete Entity in Product",e);
        }
        finally {
            con.disconnect();
        }
    }

    @Override
    public List<Entity> showAllEntity() {
        return entityList(ConstSQLTable.SHOW_ALL_PRODUCTS,-1);
    }

    @Override
    public Entity getEntityID(int id) {
       return entityList(ConstSQLTable.SHOW_PRODUCT_ID,id).get(0);
    }

    @Override
    public List<Entity> showEntityByParentId(int parentID) {
        return entityList(ConstSQLTable.SHOW_PRODUCTS_BY_CATEGORY_ID,parentID);
    }

    /**
     * Метод создает спиок Product
     * @param sql запрос SQL
     * @param id (параметр для  PreparedStatement)
     */
    @Override
    public List<Entity> entityList(String sql, int id){
        Connection connection = con.connect();
        List <Entity> productList = new ArrayList<>();
        Product product;
        Categories category;
        ResultSet resultSet;
        try {
            if(id != -1){
                PreparedStatement prStatement = connection.prepareStatement(sql);
                prStatement.setInt(1,id);
                resultSet = prStatement.executeQuery();}
            else{
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
            }
            while (resultSet.next()){
                int idProduct = resultSet.getInt("PRODUCT_ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                float price = resultSet.getFloat("PRICE");
                int isActive = resultSet.getInt("IS_ACTIVE");
                int categoryId = resultSet.getInt("CATEGORY_ID");
                product = new Product(idProduct,name,description,price,isActive,categoryId);
                category =(Categories) categoriesService.getEntityID(categoryId);
                product.setCategories(category);
                productList.add(product);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        finally {
            con.disconnect();
        }
        return productList;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
