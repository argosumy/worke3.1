package main.java.project.service;

import main.java.project.dao.DaoConnection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final DaoConnection con;
    @Autowired
    public CategoriesServiceImp(DaoConnection con) {
        this.con = con;
    }



    @Override
    public void addEntity() {
        Connection connection = con.connect();
        try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.ADD_CATEGORIES);
            prStatement.setString(1,category.getName());
            prStatement.setString(2,category.getDescription());
            prStatement.setInt(3,category.getParentId());
            prStatement.executeUpdate();

        }
        catch (SQLException e){
            LOGGER.error(e);
        }
        finally {
            con.disconnect();
        }
    }

    @Override
    public void upDateEntity(int id, Entity entity) {
        //"UPDATE BOOK_CATEGORIES SET NAME = ?,DESCRIPTION = ?,PARENT_ID = ? WHERE CATEGORY_ID = ?"
        category = category((Categories) entity,id);
        Connection connection = con.connect();
            try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.UPDATE_CATEGORY_ID);
            prStatement.setString(1,category.getName());
            prStatement.setString(2,category.getDescription());
            prStatement.setInt(3,category.getParentId());
            prStatement.setInt(4,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error(e);
        }
        finally {
            con.disconnect();
        }
    }

    /**
     * Метод подготавливает Категорию к редактированию
     * @param categoryUpDate категория пришедшая из формы categoryShow с новыми полями
     * @param id категории из базы данных, которая будет редактироваться.
     * @return возвращает категорию для редактирования
     */
    private Categories category(Categories categoryUpDate, int id){
        Categories categoryReturn = new Categories();
        category  = (Categories) getEntityID(id);
        if (categoryUpDate.getName().equals("")){
            categoryReturn.setName(category.getName());
        }
        else {
            categoryReturn.setName(categoryUpDate.getName());
        }
        if (categoryUpDate.getDescription().equals("")){
            categoryReturn.setDescription(category.getDescription());
        }
        else {
            categoryReturn.setDescription(categoryUpDate.getDescription());
        }
        if (categoryUpDate.getParentId() == 0) {
            categoryReturn.setParentId(category.getParentId());
        }
        else {
            categoryReturn.setParentId(categoryUpDate.getParentId());
        }
        return categoryReturn;
    }



    @Override
    public void deleteEntity(int id) {
        Connection connection = con.connect();
        try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.DELETE_CATEGORIES_ID);
            prStatement.setInt(1,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method delete Entiti in Categories",e);
        }
        finally {
            con.disconnect();
        }
    }

    @Override
    public List<Entity> showAllEntity() {
        return entityList(ConstSQLTable.SHOW_ALL_CATEGORIES,-1);
    }

    @Override
    public Entity getEntityID(int id) {
        return entityList(ConstSQLTable.SHOW_CATEGORIES_ID,id).get(0);
    }

    @Override
    public List<Entity> showEntityByParentId(int parentID) {
        return entityList(ConstSQLTable.SHOW_CATEGORIES_BY_PARENT,parentID);
    }
    @Override
    public List<Entity> entityList(String sql,int id){
        Connection connection = con.connect();
        List<Entity> categoriesList = new ArrayList<>();
        ResultSet resultSet;
        try{
            if(id < 0){
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            }
            else {
                PreparedStatement statement = connection.prepareStatement(sql);
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
        finally {
            con.disconnect();
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
