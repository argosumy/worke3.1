package main.java.project.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import main.java.project.dao.ConstSQLTable;
import main.java.project.entities.Categories;
import main.java.project.entities.Entity;

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
        return entityList(ConstSQLTable.SHOW_ALL_CATEGORIES,-1,con);
    }

    @Override
    public Entity getEntityID(Connection con, int id) {
        return entityList(ConstSQLTable.SHOW_CATEGORIES_ID,id,con).get(0);
    }

    @Override
    public List<Entity> showEntityByParentId(Connection con, int parentID) {
        return entityList(ConstSQLTable.SHOW_CATEGORIES_BY_PARENT,parentID,con);
    }
    @Override
    public List<Entity> entityList(String sql,int id, Connection con){
        List<Entity> categoriesList = new ArrayList<>();
        ResultSet resultSet;
        try{
            if(id < 0){
            Statement statement = con.createStatement();
            resultSet = statement.executeQuery(sql);
            }
            else {
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setInt(1,id);
                resultSet = statement.executeQuery();
            }
            while (resultSet.next()){
                int categoryId = resultSet.getInt("CATEGORY_ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                int parentId = resultSet.getInt("PARENT_ID");
                Categories categories = new Categories(categoryId,name,description,parentId);
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
