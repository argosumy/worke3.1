package entities;

import dao.ConstSQLCreatTable;
import dao.DaoEntitiesMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class Categories extends Entity implements DaoEntitiesMethod {
    private String description;
    private int parentId;
    private static final Logger LOGGER = Logger.getLogger(Categories.class);

    public Categories() {
        super();
    }

    public Categories(String name, String description, int parentId) {
        super(name);
        this.description = description;
        this.parentId = parentId;
    }

    @Override
    public void addEntiti(@Autowired Connection connection)  {
        try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLCreatTable.ADD_CATEGORIES);
            prStatement.setString(1,this.getName());
            prStatement.setString(2,description);
            prStatement.setInt(3,parentId);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method insert Entiti in Categories",e);
        };
    }

    @Override
    public void upDateEntiti(int id) {

    }

    @Override
    public void deleteEntiti(int id) {

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
}
