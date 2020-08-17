package project.entities;

import org.apache.log4j.Logger;
import project.dao.ConstSQLTable;
import project.dao.DaoEntitiesMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyUser extends Entity implements DaoEntitiesMethod {

    private String lastName;
    private String phone;
    private Account account;
    private static final Logger LOGGER = Logger.getLogger(MyUser.class);

    public MyUser() {
        super();
    }



    public MyUser(String name, String lastName, String phone, Account account) {
        super(name);
        this.lastName = lastName;
        this.phone = phone;
        this.account = account;
    }

    public MyUser(int id, String name, String lastName, String phone, Account account) {
        super(id, name);
        this.lastName = lastName;
        this.phone = phone;
        this.account = account;
    }

    @Override
    public void addEntity(Connection con) throws SQLException {
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.ADD_USER);
            prStatement.setString(1,this.getName());
            prStatement.setString(2,this.lastName);
            prStatement.setString(3,this.phone);
            prStatement.executeUpdate();
            prStatement = con.prepareStatement(ConstSQLTable.ADD_ACCOUNT);
            prStatement.setString(1,this.account.getName());
            prStatement.setString(2,this.account.getPassword());
            prStatement.setString(3,"ADMIN");
            PreparedStatement prStatementSel = con.prepareStatement("SELECT id FROM BOOK_USERS WHERE NAME = ?");
            prStatementSel.setString(1,this.getName());
            ResultSet resultSet = prStatementSel.executeQuery();
            resultSet.next();
            int id_user = resultSet.getInt("ID");
            prStatement.setInt(4,id_user);
            prStatement.executeUpdate();
        }
        catch (SQLException e){
            LOGGER.error("ERROR method insert Entiti in Product",e);
            System.out.println(e);
        }
        catch (Exception e){
            LOGGER.error("ERROR method insert Entiti in Product",e);
            System.out.println(e);
        }

    }

    @Override
    public void upDateEntity(Connection connection, int id) {

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
            LOGGER.error("ERROR method delete Entiti in Categories",e);
        }

    }

    @Override
    public Entity getEntityID(Connection connection, int id) {
        return null;
    }

    @Override
    public List<Entity> showAllEntity(Connection con) {
        List userList = new ArrayList<MyUser>();
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
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

