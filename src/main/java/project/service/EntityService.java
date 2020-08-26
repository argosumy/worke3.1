package project.service;

import project.entities.Categories;
import project.entities.Entity;

import java.sql.Connection;
import java.util.List;

public interface EntityService {
    public void addEntity(Connection connection);
    public void upDateEntity(Connection con,int id);
    public void deleteEntity(Connection con,int id);
    public List<Entity> showAllEntity(Connection con);
    public Entity getEntityID(Connection con, int id);
    public List<Entity> showEntityByParentId(Connection con, int parentID);
}
