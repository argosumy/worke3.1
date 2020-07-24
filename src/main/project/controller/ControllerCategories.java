package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dao.DaoConnection;
import project.entities.Categories;
import project.entities.Entity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerCategories {
    private Categories categories;
    private DaoConnection con;
    private static List<Entity> persons = new ArrayList<>();

  /*  static {
        persons.add(new Entity(1, "Gates") {
        });
        persons.add(new Entity(2, "Jobs") {
        });
    }*/

    @Autowired
    public void setCon(DaoConnection con) {
        this.con = con;
    }

    @GetMapping("/categoryShow")
    public String getCategoriesShow(Model model){
        Connection connection = con.connect();
        categories = new Categories();
        List<Entity> categoriesList = categories.showAllEntity(connection);
        model.addAttribute("categories",categoriesList);
        con.disconnect();
        return "categoryShow";
    }


    @PostMapping ("/addCategories")
    public String addCategories(@RequestParam(value = "name")String name,
                                @RequestParam(value = "description")String description,
                                @RequestParam(value = "id")int id, Model model){
        categories = new Categories(name,description,id);
        System.out.println(categories.toString());
        Connection connection = con.connect();
        categories.addEntity(connection);
        List<Entity> categoriesList = categories.showAllEntity(connection);
        con.disconnect();
        System.out.println("СПИСОК " + categoriesList.toString());
        model.addAttribute("categories",categoriesList);
        return "categoryShow";
    }

    @GetMapping("/delCategories/{id}")
    public String delCategories(@PathVariable(value= "id") int id){
        categories = new Categories();
        categories.deleteEntity(con.connect(),id);
        con.disconnect();
        return "categoryShow";
    }


}
