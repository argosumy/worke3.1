package project.controller;

import project.dao.DaoConnection;
import project.entities.Categories;
import project.entities.Product;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.CategoriesServiceImp;

import java.sql.Connection;
import java.util.List;

@Controller
public class ControllerBook {
    private Categories categories;
    private CategoriesServiceImp categoriesService;
    private Product product;
    private final DaoConnection con;


    @Autowired
    public ControllerBook(DaoConnection con) {
        this.con = con;
    }

    @GetMapping("/showBook/{idCategory}")
    public String headMain(@PathVariable(value = "idCategory")int idCategory,
                           Model model){
        Connection connection = con.connect();
        List<Categories> categoriesList = null;
        List<Product> productList = null;
        categoriesService = new CategoriesServiceImp();
        product = new Product();
        categoriesList = categoriesService.showEntityByParentId(connection,idCategory);
        productList = product.showEntityByParentId(connection,idCategory);
        model.addAttribute("categories",categoriesList);
        model.addAttribute("products",productList);
        model.addAttribute("category", categoriesService.getEntityID(connection,idCategory));
        con.disconnect();
        return "bookShow";
    }

    @GetMapping("/showBook/product/{id}")
    public String showProduct(@PathVariable(value = "id")int id, Model model){
        Connection connection = con.connect();
        product = new Product();
        product = (Product) product.getEntityID(connection,id);
        model.addAttribute("product", product);
        model.addAttribute("idCategory", product.getCategoryId());
        return "productShowByName";
    }


}


