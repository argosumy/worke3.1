package main.java.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.java.project.entities.Account;
import main.java.project.entities.Entity;
import main.java.project.entities.MyUser;
import main.java.project.service.MyUserServiceImp;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping(value = "/admin/user")
public class ControllerUser {
    private final MyUserServiceImp myUserService;

    @Autowired
    public ControllerUser(MyUserServiceImp myUserService) {

        this.myUserService = myUserService;
    }

    @RequestMapping("/userShow")
    public String getEntity(Model model) {
        List<Entity> myUserList = myUserService.showAllEntity();
        model.addAttribute("myUsers",myUserList);
        return "accountShow";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addEntity(@RequestParam(value = "name") String name,
                            @RequestParam(value = "lastName") String lastName,
                            @RequestParam(value = "phone") String phone,
                            @RequestParam(value = "login") String login,
                            @RequestParam(value = "password") String password,
                            Model model) {
        MyUser myUser = new MyUser(name,lastName,phone, new Account(login,password));
        myUserService.setMyUser(myUser);
        myUserService.addEntity();
        List<Entity> myUserList = myUserService.showAllEntity();
        model.addAttribute("myUsers",myUserList);
        return "accountShow";
    }
    /**
     *Метод удаления и редактирования администраторов
     */
    @RequestMapping(value = "/{action}/{id}")
    public String delCategories(@PathVariable(value= "id") int id,
                                @PathVariable(value = "action") String action,
                                Model model){
        MyUser myUser;
        List<Entity> myUsersList = new ArrayList<>();
        if(id != 1) { //ограничение на удаление первого администратора
            if (action.equals("del")) {
                myUserService.deleteEntity(id);
                myUsersList = myUserService.showAllEntity();
                model.addAttribute("myUsers", myUsersList);
            }
            if (action.equals("upDate")) {
                myUser = (MyUser) myUserService.getEntityID(id);
                myUsersList.add(myUser);
                model.addAttribute("myUsers", myUsersList);
                model.addAttribute("upDate", true);
            }
        }
        else{
            myUsersList = myUserService.showAllEntity();
            model.addAttribute("myUsers", myUsersList);
        }
        return "accountShow";
    }

    @RequestMapping(value = "/updateUser/{id}",method = RequestMethod.POST)
    public String updateCategory(@PathVariable(value = "id")int id,
                                 @RequestParam(value = "name", defaultValue = "") String name,
                                 @RequestParam(value = "lastName", defaultValue = "") String lastName,
                                 @RequestParam(value = "phone", defaultValue = "") String phone,
                                 @RequestParam(value = "login", defaultValue = "") String login,
                                 @RequestParam(value = "password", defaultValue = "") String password,
                                 Model model){

        myUserService.upDateEntity(id, new MyUser(name, lastName, phone, new Account(login, password,id)));
        List<Entity> res = myUserService.showAllEntity();
        model.addAttribute("myUsers",res);
        return "accountShow";
    }
}
