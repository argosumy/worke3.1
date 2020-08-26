package project.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import project.dao.ConstSQLTable;
import project.entities.Categories;
import project.entities.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesServiceImp implements EntityService {
    private Categories category;
    private static final Logger LOGGER = Logger.getLogger(CategoriesServiceImp.class);

    public CategoriesServiceImp() {
    }

    public CategoriesServiceImp(Categories category) {
        this.category = category;
    }

    @Override
    public void addEntity(Connection connection) {
        try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.ADD_CATEGORIES);
            prStatement.setString(1,category.getName());
            prStatement.setString(2,category.getDescription());
            prStatement.setInt(3,category.getParentId());
            prStatement.executeUpdate();

        }
        catch (SQLException e){
            LOGGER.error("ERROR method insert Entity in Categories",e);
        }

    }

    @Override
    public void upDateEntity(Connection con, int id) {
        //"UPDATE BOOK_CATEGORIES SET NAME = ?,DESCRIPTION = ?,PARENT_ID = ? WHERE CATEGORY_ID = ?"
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.UPDATE_CATEGORY_ID);
            prStatement.setString(1,category.getName());
            prStatement.setString(2,category.getDescription());
            prStatement.setInt(3,category.getParentId());
            prStatement.setInt(4,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method delete Entiti in Categories",e);
        }
    }

    @Override
    public void deleteEntity(Connection con, int id) {
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.DELETE_CATEGORIES_ID);
            prStatement.setInt(1,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method delete Entiti in Categories",e);
        }
    }

    @Override
    public List<Entity> showAllEntity(Connection con) {
        List<Entity> categoriesList = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(ConstSQLTable.SHOW_ALL_CATEGORIES);
            while (resultSet.next()){
                int id = resultSet.getInt("CATEGORY_ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                int parentId = resultSet.getInt("PARENT_ID");
                Categories categories = new Categories(id,name,description,parentId);
                categoriesList.add(categories);
            }
        }
        catch (SQLException e){
            LOGGER.error("ERROR method showAllEntity in Categories",e);
        }
        return categoriesList;
    }

    @Override
    public Entity getEntityID(Connection con, int id) {
        Entity category = null;
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.SHOW_CATEGORIES_ID);
            prStatement.setInt(1,id);
            ResultSet resultSet = prStatement.executeQuery();
            resultSet.next();
            int idCategory = resultSet.getInt("CATEGORY_ID");
            String name = resultSet.getString("NAME");
            String description = resultSet.getString("DESCRIPTION");
            int parentId = resultSet.getInt("PARENT_ID");
            category = new Categories(idCategory,name,description,parentId);
        }
        catch (SQLException e){
            LOGGER.error("ERROR method showEntityID in Categories",e);
        }
        return category ;
    }

    @Override
    public List<Entity> showEntityByParentId(Connection con, int parentID) {
        List <Entity> categoriesList = new ArrayList<>();
        try{
            PreparedStatement statement = con.prepareStatement(ConstSQLTable.SHOW_CATEGORIES_BY_PARENT);
            statement.setInt(1,parentID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("CATEGORY_ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                int parentId = resultSet.getInt("PARENT_ID");
                Categories categories = new Categories(id,name,description,parentId);
                categoriesList.add(categories);
            }
        }
        catch (SQLException e){
            LOGGER.error("ERROR method showAllEntity in Categories",e);
        }
        return categoriesList;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}
