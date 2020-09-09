package main.java.project.service;

import main.java.project.dao.DaoConnection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.java.project.dao.ConstSQLTable;
import main.java.project.entities.Account;
import main.java.project.entities.Entity;
import main.java.project.entities.MyUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class MyUserServiceImp implements EntityService {
    private MyUser myUser;
    private static final Logger LOGGER = Logger.getLogger(MyUserServiceImp.class);
    private final DaoConnection con;
    @Autowired
    public MyUserServiceImp(DaoConnection con) {
        this.con = con;
    }

    @Override
    public void addEntity() {
        Connection connection = con.connect();
        try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.ADD_USER);
            prStatement.setString(1,myUser.getName());
            prStatement.setString(2,myUser.getLastName());
            prStatement.setString(3,myUser.getPhone());
            prStatement.executeUpdate();
            prStatement = connection.prepareStatement(ConstSQLTable.ADD_ACCOUNT);
            prStatement.setString(1,myUser.getAccount().getName());
            prStatement.setString(2,myUser.getAccount().getPassword());
            prStatement.setString(3,"ADMIN");
            PreparedStatement prStatementSel = connection.prepareStatement("SELECT id FROM BOOK_USERS WHERE NAME = ?");
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
        finally {
            con.disconnect();
        }
    }

    @Override
    public void upDateEntity(int id, Entity entity) {
        Connection connection = con.connect();
        myUser = myUser(id, (MyUser) entity);
        try {
            //UPDATE BOOK_USERS SET NAME = ?,LAST_NAME = ?,PHONE = ? WHERE ID = ?";
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.UPDATE_USER_ID);
            prStatement.setString(1,myUser.getName());
            prStatement.setString(2,myUser.getLastName());
            prStatement.setString(3,myUser.getPhone());
            prStatement.setInt(4,id);
            prStatement.executeUpdate();
            //UPDATE BOOK_LOGIN SET LOGIN = ?,PASSWORD = ? WHERE ID_USER = ?
            prStatement = connection.prepareStatement(ConstSQLTable.UPDATE_ACCOUNT_ID_USER);
            prStatement.setString(1,myUser.getAccount().getName());
            prStatement.setString(2,myUser.getAccount().getPassword());
            prStatement.setInt(3,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error(e);
        }
        finally {
            con.disconnect();
        }
    }
    /**
     * Метод подготавливает данные пользователя к редактированию
     * @param entityUpDate данные пользователя пришедшие из формы accountShow с новыми полями
     * @param id пользователя из базы данных, который будет редактироваться.
     * @return возвращает пользователя для редактирования
     */
    private MyUser myUser(int id, MyUser entityUpDate){
        myUser = (MyUser) getEntityID(id);
        MyUser myUserReturn = new MyUser();
        Account account = new Account();
        if (entityUpDate.getName().equals("")){
            myUserReturn.setName(myUser.getName());
        }
        else {
            myUserReturn.setName(entityUpDate.getName());
        }
        if (entityUpDate.getLastName().equals("")){
            myUserReturn.setLastName(myUser.getLastName());
        }
        else {
            myUserReturn.setLastName(entityUpDate.getLastName());
        }
        if (entityUpDate.getPhone().equals("")) {
            myUserReturn.setPhone(myUser.getPhone());
        }
        else {
            myUserReturn.setPhone(entityUpDate.getPhone());
        }
        if (entityUpDate.getAccount().getName().equals("")){
            account.setName(myUser.getAccount().getName());
        }
        else {
            account.setName(entityUpDate.getAccount().getName());
        }
        if(entityUpDate.getAccount().getPassword().equals("")){
            account.setPassword(myUser.getAccount().getPassword());
        }
        else account.setPassword(entityUpDate.getAccount().getPassword());
        myUserReturn.setAccount(account);
        return myUserReturn;
    }

    @Override
    public void deleteEntity(int id) {
        Connection connection = con.connect();
        try {
            PreparedStatement prStatement = connection.prepareStatement(ConstSQLTable.DELETE_LOGIN_ID_USER);
            prStatement.setInt(1,id);
            prStatement.executeUpdate();
            prStatement = connection.prepareStatement(ConstSQLTable.DELETE_USERS_ID);
            prStatement.setInt(1,id);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method delete Entity in Categories",e);
        }
        finally {
            con.disconnect();
        }
    }

    @Override
    public List<Entity> showAllEntity() {
        return entityList(ConstSQLTable.SHOW_ALL_ACCOUNTS,-1);
    }

    @Override
    public Entity getEntityID(int id) {
        return entityList(ConstSQLTable.SHOW_ACCOUNTS_BY_ID,id).get(0);
    }

    @Override
    public List<Entity> entityList(String sql, int id) {
        Connection connection = con.connect();
        List<Entity>userList = new ArrayList<>();
        ResultSet resultSet;
        MyUser user;
        try {
        if(id < 0){
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        }
        else {
            PreparedStatement preparedStatement =connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
        }
            while (resultSet.next()){
                int userId = resultSet.getInt(1);
                String name = resultSet.getString("NAME");
                String lastName = resultSet.getString("LAST_NAME");
                String phone = resultSet.getString("PHONE");
                String login = resultSet.getString("LOGIN");
                String password = resultSet.getString("PASSWORD");
                String role = resultSet.getString("ROLE");
                Account account = new Account(login,password,userId);
                user = new MyUser(userId,name,lastName,phone,account);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            con.disconnect();
        }
        return userList;
    }

    @Override
    public List<Entity> showEntityByParentId(int parentID) {
        return null;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }
}
