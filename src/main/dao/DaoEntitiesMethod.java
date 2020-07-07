package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoEntitiesMethod {
    public void addEntiti(Connection connection) throws SQLException;
    public void upDateEntiti(int id);
    public void deleteEntiti(int id);
}
