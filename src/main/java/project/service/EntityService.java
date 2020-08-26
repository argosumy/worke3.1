package project.service;

import project.entities.Entity;

import java.sql.Connection;
import java.util.List;

public interface EntityService {
    void addEntity(Connection connection);
    void upDateEntity(Connection con,int id);
    void deleteEntity(Connection con,int id);
    List<Entity> showAllEntity(Connection con);
    Entity getEntityID(Connection con, int id);
    List<Entity> showEntityByParentId(Connection con, int parentID);
}
