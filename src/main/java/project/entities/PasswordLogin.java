package project.entities;

public class PasswordLogin extends Entity {
    private String password;
    private final String ROLE = "ADMIN";
    private int id_user;

    public PasswordLogin() {
    }

    public PasswordLogin(String login, String password, int id_user) {
        super(login);
        this.password = password;
        this.id_user = id_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getROLE() {
        return ROLE;
    }
}
