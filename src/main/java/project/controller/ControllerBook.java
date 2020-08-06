package project.controller;

import project.dao.DaoConnection;
import project.entities.Categories;
import project.entities.Product;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.util.List;

@Controller
public class ControllerBook {
    private Categories categories;
    private Product product;
    private DaoConnection con;


    @Autowired
    public void setCon(DaoConnection con) {
        this.con = con;
    }

    @GetMapping("/showBook/{idCategory}")
    public String headMain(@PathVariable(value = "idCategory")int idCategory,
                           Model model){
        Connection connection = con.connect();
        List<Categories> categoriesList = null;
        List<Product> productList = null;
        categories = new Categories();
        product = new Product();

        categoriesList = categories.showEntityByParentId(connection,idCategory);
        productList = product.showEntityByParentId(connection,idCategory);
        model.addAttribute("categories",categoriesList);
        model.addAttribute("products",productList);

        con.disconnect();
        return "bookShow";
    }

    @GetMapping("/showBook/product/{id}")
    public String showProduct(@PathVariable(value = "id")int id, Model model){
        Connection connection = con.connect();
        product = new Product();
        model.addAttribute("product",product.getEntityID(connection,id));
        return "productShowByName";
    }


}


