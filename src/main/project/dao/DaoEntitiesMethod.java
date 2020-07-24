package project.dao;

import org.apache.poi.ss.formula.functions.T;
import project.entities.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DaoEntitiesMethod {
    public void addEntity(Connection connection) throws SQLException;
    public void upDateEntity(int id);
    public void deleteEntity(Connection connection,int id);
    public List<Entity> showAllEntity(Connection connection);
    public List<Entity> showAllEntityCategory(Connection connection);
    }
