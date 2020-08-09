package project.entities;

import org.apache.log4j.Logger;
import project.dao.ConstSQLTable;
import project.dao.DaoEntitiesMethod;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordLogin extends Entity implements DaoEntitiesMethod {
    private static final Logger LOGGER = Logger.getLogger(PasswordLogin.class);
    private String password;
    private String role;
    private int id_user;

    public PasswordLogin() {
    }

    public PasswordLogin(String login, String password, int id_user) {
        super(login);
        this.password = password;
        this.id_user = id_user;
        this.role = "ADMIN";
    }

    @Override
    public void addEntity(Connection connection) throws SQLException {

    }

    @Override
    public void upDateEntity(Connection connection, int id) {

    }

    @Override
    public void deleteEntity(Connection connection, int id) {

    }

    @Override
    public Entity getEntityID(Connection connection, int id) {
        return null;
    }

    @Override
    public List<Entity> showAllEntity(Connection con) {
        List accountList = new ArrayList<PasswordLogin>();
        try{
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(ConstSQLTable.SHOW_ALL_ACCOUNTS);
            while (resultSet.next()){
                //int id = resultSet.getInt("ID");
                String login = resultSet.getString("LOGIN");
                String password = resultSet.getString("PASSWORD");
                int userId = resultSet.getInt("ID_USER");
                PasswordLogin account = new PasswordLogin(login,password,userId);
                accountList.add(account);
            }
        }
        catch (SQLException e){
            LOGGER.error("ERROR method showAllEntity in Categories",e);
        }
        return accountList;
    }

    public PasswordLogin getEntityName(Connection con, String login){
        PasswordLogin account = null;
        try {
            PreparedStatement prStatement = con.prepareStatement(ConstSQLTable.SELECT_LOGIN_PASSWORD_ROLE);
            prStatement.setString(1, login);
            ResultSet resultSet = prStatement.executeQuery();
            resultSet.next();
            String loginNew = resultSet.getString("LOGIN");
            String password = resultSet.getString("PASSWORD");
            int idUser = resultSet.getInt("ID_USER");
            account = new PasswordLogin(loginNew,password,id_user);
        }
        catch (SQLException e){
            LOGGER.error("ERROR method showEntityID in Products",e);
        }
        return account;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getROLE() {
        return role;
    }
}
