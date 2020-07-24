package project.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.dao.DaoConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.entities.Categories;
import project.entities.Entity;

import java.sql.Connection;
import java.util.List;

@Controller
public class ControllerMain {
    private Categories categories;
    private DaoConnection con;

    @Autowired
    public void setCon(DaoConnection con) {
        this.con = con;
    }


    public String headMain(){
        return "addCategories";
    }


}


