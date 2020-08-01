package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.dao.DaoConnection;
import project.entities.Entity;
import project.entities.Product;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerProduct {
    Product product;
    DaoConnection con;

    @Autowired
    public void setCon(DaoConnection con) {
        this.con = con;
    }

    @GetMapping("/productShow")
    public String getCategoriesShow(Model model) {
        Connection connection = con.connect();
        product = new Product();
        List<Entity> productsList = product.showAllEntity(connection);
        model.addAttribute("products", productsList);
        con.disconnect();
        return "productsShow";
    }
    @PostMapping("/addProduct")
    public String addCategories(@RequestParam(value = "name")String name,
                                @RequestParam(value = "description")String description,
                                @RequestParam(value = "price")float price,
                                @RequestParam(value = "isActive")int isActive,
                                @RequestParam(value = "categoryId")int categoryId,
                                Model model){
        product = new Product(name,description,price,isActive,categoryId);
        System.out.println(product.toString());
        Connection connection = con.connect();
        product.addEntity(connection);
        List<Entity> productsList = product.showAllEntity(connection);
        con.disconnect();
        System.out.println("СПИСОК " + productsList.toString());
        model.addAttribute("products",productsList);
        return "productsShow";
    }

    @GetMapping("/Product/{action}/{id}")
    public String delProduct(@PathVariable(value= "id") int id,
                                @PathVariable(value = "action") String action,
                                Model model){
        Connection connection = con.connect();
        product = new Product();
        List<Entity> productsList = new ArrayList<>();
        if(action.equals("del")){
            product.deleteEntity(connection,id);
            productsList = product.showAllEntity(connection);
            model.addAttribute("products",productsList);
        }
        if(action.equals("upDate")){
            product = (Product) product.getEntityID(connection,id);
            productsList.add(product);
            model.addAttribute("products",productsList);
            model.addAttribute("upDate", true);
        }
        con.disconnect();
        return "productsShow";
    }

}
