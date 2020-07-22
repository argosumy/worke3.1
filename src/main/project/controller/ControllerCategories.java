package project.controller;

import oracle.jrockit.jfr.StringConstantPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.dao.DaoConnection;
import project.dao.OracleDaoConnection;
import project.entities.Categories;

@Controller
public class ControllerCategories {
    private Categories categories;
    private DaoConnection con;

    @Autowired
    public void setCon(DaoConnection con) {
        this.con = con;
    }

    @GetMapping("/addCategories")
    public String getCategoriesForm(){
        return "start";
    }

    @PostMapping ("/addCategories")
    public String addCategories(@RequestParam(value = "name")String name,
                                @RequestParam(value = "description")String description,
                                @RequestParam(value = "id")int id){
        categories = new Categories(name,description,id);
        System.out.println(categories.toString());
        categories.addEntity(con.connect());
        con.disconnect();
        System.out.println(categories.toString());
        return "start";
    }

    @GetMapping("/del")
    public String delCategories(){
        categories.deleteEntity(con.connect(),1);
        con.disconnect();
        return null;
    }


}
