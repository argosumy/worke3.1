package main.project.dao;

import main.project.entities.Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DaoEntitiesMethod {
    void addEntity(Connection connection) throws SQLException;
    void upDateEntity(Connection connection,int id);
    void deleteEntity(Connection connection,int id);
    Entity getEntityID(Connection connection, int id);
    List<Entity> showAllEntity(Connection connection);
    }
