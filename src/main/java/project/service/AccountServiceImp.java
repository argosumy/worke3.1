package project.service;

import org.apache.log4j.Logger;
import project.dao.ConstSQLTable;
import project.entities.Account;
import project.entities.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountServiceImp implements EntityService {
    private static final Logger LOGGER = Logger.getLogger(AccountServiceImp.class);
    private Account account;

    @Override
    public void addEntity(Connection connection) {


    }

    @Override
    public void upDateEntity(Connection con, int id) {

    }

    @Override
    public void deleteEntity(Connection con, int id) {

    }

    @Override
    public List<Entity> showAllEntity(Connection con) {
        List<Entity> accountList = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
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

    @Override
    public Entity getEntityID(Connection con, int id) {
        return null;
    }

    @Override
    public List<Entity> showEntityByParentId(Connection con, int parentID) {
        return null;
    }

    public Account getEntityName(Connection con, String login){
        Account account = null;
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.SELECT_LOGIN_PASSWORD_ROLE);
            prStatement.setString(1, login);
            ResultSet resultSet = prStatement.executeQuery();
            resultSet.next();
            String loginNew = resultSet.getString("LOGIN");
            String password = resultSet.getString("PASSWORD");
            int idUser = resultSet.getInt("ID_USER");
            account = new Account(loginNew,password,idUser);
        }
        catch (SQLException e){
            LOGGER.error("ERROR method showEntityID in AccountServiceImp",e);
        }
        return account;
    }
}
