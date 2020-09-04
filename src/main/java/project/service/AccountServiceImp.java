package main.java.project.service;

import main.java.project.dao.DaoConnection;
import main.java.project.entities.Account;
import main.java.project.entities.Entity;
import org.apache.log4j.Logger;
import main.java.project.dao.ConstSQLTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class AccountServiceImp {
    private static final Logger LOGGER = Logger.getLogger(AccountServiceImp.class);
    private Account account;
    private DaoConnection con;

    @Autowired
    public AccountServiceImp(DaoConnection con) {
        this.con = con;
    }


    public List<Entity> showAllEntity() {
        Connection connection = con.connect();
        List<Entity> accountList = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(ConstSQLTable.SHOW_ALL_ACCOUNTS);
            while (resultSet.next()){
                //int id = resultSet.getInt("ID");
                String login = resultSet.getString("LOGIN");
                String password = resultSet.getString("PASSWORD");
                int userId = resultSet.getInt("ID");
                Account account = new Account(login,password,userId);
                accountList.add(account);
            }
        }
        catch (SQLException e){
            LOGGER.error("ERROR method showAllEntity in AccountServiceImp",e);
        }
        return accountList;
    }

    public Account getEntityName(String login){
        Connection connection = con.connect();
        try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.SELECT_LOGIN_PASSWORD_ROLE);
            prStatement.setString(1, login);
            ResultSet resultSet = prStatement.executeQuery();
            resultSet.next();
            String loginNew = resultSet.getString("LOGIN");
            String password = resultSet.getString("PASSWORD");
            int idUser = resultSet.getInt("ID_USER");
            account = new Account(loginNew,password,idUser);
        }
        catch (SQLException e){
            LOGGER.error(e);
        }
        finally {
            con.disconnect();
        }
        return account;
    }

}
