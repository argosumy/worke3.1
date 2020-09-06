package main.java.project.entities;

public class MyUser extends Entity {

    private String lastName;
    private String phone;
    private Account account;

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

