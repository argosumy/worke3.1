package project.entities;

public class User extends Entity {
    private String lastName;
    private String phone;

    public User(String name, String lastName, String phone) {
        super(name);
        this.lastName = lastName;
        this.phone = phone;
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

