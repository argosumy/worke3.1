package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.dao.DaoConnection;
import project.entities.Account;
import project.entities.Categories;
import project.entities.Entity;
import project.entities.MyUser;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Controller
public class ControllerUser implements ControllerEntities {
    private final DaoConnection con;
    private MyUser myUser;

    @Autowired
    public ControllerUser(DaoConnection con) {
        this.con = con;
    }

    @Override
    @GetMapping("/admin/userShow")
    public String getEntity(Model model) {
        Connection connection = con.connect();
        myUser = new MyUser();
        List<Entity> myUserList = myUser.showAllEntity(connection);
        model.addAttribute("myUsers",myUserList);
        con.disconnect();
        return "accountShow";
    }


    @PostMapping("/admin/addUser")
    public String addEntity(@RequestParam(value = "name") String name,
                            @RequestParam(value = "lastName") String lastName,
                            @RequestParam(value = "phone") String phone,
                            @RequestParam(value = "login") String login,
                            @RequestParam(value = "password") String password,
                            Model model) {
        Connection connection = con.connect();
        myUser = new MyUser();
        myUser.setName(name);
        myUser.setLastName(lastName);
        myUser.setPhone(phone);
        Account account = new Account();
        account.setName(login);
        account.setPassword(password);
        myUser.setAccount(account);
        try {
            myUser.addEntity(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Entity> myUserList = myUser.showAllEntity(connection);
        con.disconnect();
        model.addAttribute("myUsers",myUserList);
        return "accountShow";
    }
    /**
     *Метод удаления и редактирования администраторов
     */
    @GetMapping("/admin/User/{action}/{id}")
    public String delCategories(@PathVariable(value= "id") int id,
                                @PathVariable(value = "action") String action,
                                Model model){
        if(id != 1) { //ограничение на удаление первого администратора
            Connection connection = con.connect();
            myUser = new MyUser();
            List<Entity> myUsersList = new ArrayList<>();
            if (action.equals("del")) {
                myUser.deleteEntity(connection, id);
                myUsersList = myUser.showAllEntity(connection);
                model.addAttribute("myUsers", myUsersList);
            }
            if (action.equals("upDate")) {
                myUser = (MyUser) myUser.getEntityID(connection, id);
                myUsersList.add(myUser);
                model.addAttribute("myUsers", myUsersList);
                model.addAttribute("upDate", true);
            }
            con.disconnect();
        }
        return "accountShow";
    }
}
