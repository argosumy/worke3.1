package main.java.project.entities;

public class Account extends Entity  {
    private String password;
    private String role;
    private int id_user;


    public Account(){}
    public Account(String login, String password) {
        super(login);
        this.password = password;
        this.role = "ADMIN";
    }

    public Account(String login, String password, int id_user) {
        super(login);
        this.password = password;
        this.id_user = id_user;
        this.role = "ADMIN";
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
