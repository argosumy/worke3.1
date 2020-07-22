package project.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoEntitiesMethod {
    public void addEntity(Connection connection) throws SQLException;
    public void upDateEntity(int id);
    public void deleteEntity(Connection connection,int id);
}
