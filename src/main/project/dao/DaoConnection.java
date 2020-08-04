package main.project.dao;

import java.sql.Connection;

public interface DaoConnection {
    Connection connect() ;
    void disconnect();
    void creatTable();

}
