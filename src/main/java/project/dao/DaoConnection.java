package main.java.project.dao;

import javax.sql.DataSource;
import java.sql.Connection;

public interface DaoConnection {
    Connection connect() ;
    void disconnect();
    void creatTable();
    DataSource getDataSource();

}
