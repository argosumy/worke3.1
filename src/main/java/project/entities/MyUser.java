package project.entities;

import project.dao.DaoEntitiesMethod;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class MyUser extends Entity implements DaoEntitiesMethod {
    private String lastName;
    private String phone;

    public MyUser(String name, String lastName, String phone) {
        super(name);
        this.lastName = lastName;
        this.phone = phone;
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
    public List<Entity> showAllEntity(Connection connection) {
        return null;
    }

    public MyUser getEntityName(Connection con, String login){
        MyUser user = null;

        return user;
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

