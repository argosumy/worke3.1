package project.entities;

import org.apache.log4j.Logger;
import project.dao.ConstSQLTable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Account extends Entity  {
    private static final Logger LOGGER = Logger.getLogger(Account.class);
    private String password;
    private String role;
    private int id_user;


    public Account(){}

    public Account(String login, String password, int id_user) {
        super(login);
        this.password = password;
        this.id_user = id_user;
        this.role = "ADMIN";
    }

    public List<Entity> showAllEntity(Connection con) {
        List accountList = new ArrayList<Account>();
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
            LOGGER.error("ERROR method showAllEntity in Categories",e);
        }
        return accountList;
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
            account = new Account(loginNew,password,id_user);
        }
        catch (SQLException e){
            LOGGER.error("ERROR method showEntityID in Products",e);
        }
        return account;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
