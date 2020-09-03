package main.java.project.service;

import main.java.project.entities.Categories;
import main.java.project.entities.Entity;
import main.java.project.entities.Product;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import main.java.project.dao.ConstSQLTable;

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
        return entityList(ConstSQLTable.SHOW_ALL_PRODUCTS,-1,con);
    }

    @Override
    public Entity getEntityID(Connection con, int id) {
       return entityList(ConstSQLTable.SHOW_PRODUCT_ID,id,con).get(0);
    }

    @Override
    public List<Entity> showEntityByParentId(Connection con, int parentID) {
        return entityList(ConstSQLTable.SHOW_PRODUCTS_BY_CATEGORY_ID,parentID,con);
    }

    /**
     * Метод создает спиок Product
     * @param sql запрос SQL
     * @param id (параметр для  PreparedStatement)
     * @param con (Connection)
     */
    @Override
    public List<Entity> entityList(String sql, int id, Connection con){
        List <Entity> productList = new ArrayList<>();
        Product product;
        Categories category;
        CategoriesServiceImp categoriesService = new CategoriesServiceImp();
        ResultSet resultSet;
        try {
            if(id != -1){
                PreparedStatement prStatement = con.prepareStatement(sql);
                prStatement.setInt(1,id);
                resultSet = prStatement.executeQuery();}
            else{
                Statement statement = con.createStatement();
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
                category =(Categories) categoriesService.getEntityID(con,categoryId);
                product.setCategories(category);
                productList.add(product);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return productList;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
