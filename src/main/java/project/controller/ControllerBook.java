package main.java.project.controller;

import main.java.project.entities.Entity;
import main.java.project.entities.Product;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import main.java.project.service.CategoriesServiceImp;
import main.java.project.service.ProductServiceImp;

import java.util.List;

@Controller
public class ControllerBook {
    private CategoriesServiceImp categoriesService;
    private ProductServiceImp productService;


    @Autowired
    public ControllerBook(ProductServiceImp productService, CategoriesServiceImp categoriesService) {
        this.productService = productService;
        this.categoriesService = categoriesService;
    }

    @GetMapping("/showBook/{idCategory}")
    public String headMain(@PathVariable(value = "idCategory")int idCategory,
                           Model model){
        List<Entity> categoriesList = categoriesService.showEntityByParentId(idCategory);
        List<Entity> productList = productService.showEntityByParentId(idCategory);
        model.addAttribute("categories",categoriesList);
        model.addAttribute("products",productList);
        model.addAttribute("category", categoriesService.getEntityID(idCategory));
        return "bookShow";
    }

    @GetMapping("/showBook/product/{id}")
    public String showProduct(@PathVariable(value = "id")int id, Model model){
        Product product = (Product) productService.getEntityID(id);
        model.addAttribute("product", product);
        model.addAttribute("idCategory", product.getCategoryId());
        return "productShowByName";
    }

}


