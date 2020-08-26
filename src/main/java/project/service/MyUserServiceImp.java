package project.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import project.dao.ConstSQLTable;
import project.entities.Account;
import project.entities.Entity;
import project.entities.MyUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class MyUserServiceImp implements EntityService {
    private MyUser myUser;
    private static final Logger LOGGER = Logger.getLogger(MyUserServiceImp.class);

    public MyUserServiceImp() {
    }

    @Override
    public void addEntity(Connection con) {
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.ADD_USER);
            prStatement.setString(1,myUser.getName());
            prStatement.setString(2,myUser.getLastName());
            prStatement.setString(3,myUser.getPhone());
            prStatement.executeUpdate();
            prStatement = con.prepareStatement(ConstSQLTable.ADD_ACCOUNT);
            prStatement.setString(1,myUser.getAccount().getName());
            prStatement.setString(2,myUser.getAccount().getPassword());
            prStatement.setString(3,"ADMIN");
            PreparedStatement prStatementSel = con.prepareStatement("SELECT id FROM BOOK_USERS WHERE NAME = ?");
            prStatementSel.setString(1,myUser.getName());
            ResultSet resultSet = prStatementSel.executeQuery();
            resultSet.next();
            int id_user = resultSet.getInt("ID");
            prStatement.setInt(4,id_user);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method insert Entity in Users",e);
        }
    }

    @Override
    public void upDateEntity(Connection con, int id) {
        try {
            //UPDATE BOOK_USERS SET NAME = ?,LAST_NAME = ?,PHONE = ? WHERE ID = ?";
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.UPDATE_USER_ID);
            prStatement.setString(1,myUser.getName());
            prStatement.setString(2,myUser.getLastName());
            prStatement.setString(3,myUser.getPhone());
            prStatement.setInt(4,id);
            prStatement.executeUpdate();
            //UPDATE BOOK_LOGIN SET LOGIN = ?,PASSWORD = ? WHERE ID_USER = ?
            prStatement = con.prepareStatement(ConstSQLTable.UPDATE_ACCOUNT_ID_USER);
            prStatement.setString(1,myUser.getAccount().getName());
            prStatement.setString(2,myUser.getAccount().getPassword());
            prStatement.setInt(3,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method delete Entity in Categories",e);
        }
    }

    @Override
    public void deleteEntity(Connection con, int id) {
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.DELETE_LOGIN_ID_USER);
            prStatement.setInt(1,id);
            prStatement.executeUpdate();
            prStatement = con.prepareStatement(ConstSQLTable.DELETE_USERS_ID);
            prStatement.setInt(1,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method delete Entity in Categories",e);
        }
    }

    @Override
    public List<Entity> showAllEntity(Connection con) {
        List<Entity>userList = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(ConstSQLTable.SHOW_ALL_ACCOUNTS);
            while (resultSet.next()){
                int userId = resultSet.getInt(1);
                String name = resultSet.getString("NAME");
                String lastName = resultSet.getString("LAST_NAME");
                String phone = resultSet.getString("PHONE");
                String login = resultSet.getString("LOGIN");
                String password = resultSet.getString("PASSWORD");
                String role = resultSet.getString("ROLE");
                Account account = new Account(login,password,userId);
                MyUser user = new MyUser(userId,name,lastName,phone,account);
                userList.add(user);
            }
        }
        catch (SQLException e){
            LOGGER.error("ERROR method showAllEntity in MyUser",e);
        }
        return userList;
    }

    @Override
    public Entity getEntityID(Connection con, int id) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        MyUser user = null;
        try {
            preparedStatement = con.prepareStatement(ConstSQLTable.SHOW_ACCOUNTS_BY_ID);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int userId = resultSet.getInt(1);
            String name = resultSet.getString("NAME");
            String lastName = resultSet.getString("LAST_NAME");
            String phone = resultSet.getString("PHONE");
            String login = resultSet.getString("LOGIN");
            String password = resultSet.getString("PASSWORD");
            String role = resultSet.getString("ROLE");
            Account account = new Account(login,password,userId);
            user = new MyUser(userId,name,lastName,phone,account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Entity> showEntityByParentId(Connection con, int parentID) {
        return null;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }
}
