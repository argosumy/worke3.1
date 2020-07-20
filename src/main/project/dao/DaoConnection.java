package project.dao;

import java.sql.Connection;

public interface DaoConnection {
    public Connection connect() ;
    public void disconnect();
    public void creatTable();

}
