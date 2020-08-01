package project.entities;

import project.dao.ConstSQLTable;
import project.dao.DaoEntitiesMethod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class Categories extends Entity implements DaoEntitiesMethod {
    public String description;
    public int parentId;
    private static final Logger LOGGER = Logger.getLogger(Categories.class);



    public Categories() {
        super();
    }

    public Categories(String name, String description, int parentId) {
        super(name);
        this.description = description;
        this.parentId = parentId;
    }
    public Categories(int id, String name, String description, int parentId) {
        super(id,name);
        this.description = description;
        this.parentId = parentId;
    }

    @Override
    public void addEntity(Connection connection)  {
        try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.ADD_CATEGORIES);
            prStatement.setString(1,this.getName());
            prStatement.setString(2,description);
            prStatement.setInt(3,parentId);
            prStatement.executeUpdate();

        }
        catch (SQLException e){
            LOGGER.error("ERROR method insert Entiti in Categories",e);
            System.out.println(e);
        }
        catch (Exception e){
            LOGGER.error("ERROR method insert Entiti in Categories",e);
            System.out.println(e);
        }

    }

    @Override
    public void upDateEntity(Connection con,int id) {
        //"UPDATE BOOK_CATEGORIES SET NAME = ?,DESCRIPTION = ?,PARENT_ID = ? WHERE CATEGORY_ID = ?"
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.UPDATE_CATEGORY_ID);
            prStatement.setString(1,this.getName());
            prStatement.setString(2,this.getDescription());
            prStatement.setInt(3,this.getParentId());
            prStatement.setInt(4,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method delete Entiti in Categories",e);
        }
    }

    @Override
    public void deleteEntity(Connection con,int id) {
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
        List categoriesList = new ArrayList<Categories>();
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(ConstSQLTable.SHOW_ALL_CATEGORIES);
            while (resultSet.next()){
                int id = resultSet.getInt("CATEGORY_ID");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                int parentId = resultSet.getInt("PARENT_ID");
                Categories categories = new Categories(id,name,description,parentId);
                System.out.println(categories.toString());
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
    public List<Entity> showEntityByParentId(Connection connection) {
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.description + " " + this.parentId;
    }
}
