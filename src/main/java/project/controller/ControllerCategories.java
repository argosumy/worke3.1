package project.controller;

import project.dao.DaoConnection;
import project.entities.Categories;
import project.entities.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.service.CategoriesServiceImp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerCategories {
    private Categories categories;
    private CategoriesServiceImp categoriesService;
    private final DaoConnection con;


    @Autowired
    public ControllerCategories(DaoConnection con, CategoriesServiceImp categoriesService) {
        this.con = con;
        this.categoriesService = categoriesService;
    }

    @GetMapping("/admin/categoryShow")
    public String getCategoriesShow(Model model){
        Connection connection = con.connect();
        List<Entity> categoriesList = categoriesService.showAllEntity(connection);
        model.addAttribute("categories",categoriesList);
        con.disconnect();
        return "categoryShow";
    }

    /**
     * Метод добавления новой строки в таблицу BOOK_CATEGORIES
     * @param name Название категории
     * @param description Описание категории
     * @param id ID родительской категории
     * @param model categories - обновленный список всех категории после добавления новой
     * @return
     */
    @PostMapping ("/admin/addCategories")
    public String addCategories(@RequestParam(value = "name")String name,
                                @RequestParam(value = "description")String description,
                                @RequestParam(value = "parentId", defaultValue = "1")int id, Model model){
        categories = new Categories(name,description,id);
        categoriesService = new CategoriesServiceImp(categories);
        Connection connection = con.connect();
        categoriesService.addEntity(connection);
        List<Entity> categoriesList = categoriesService.showAllEntity(connection);
        con.disconnect();
        model.addAttribute("categories",categoriesList);
        return "categoryShow";
    }

    /**
     *Метод удаления и редактирования категории
     */
    @GetMapping("/admin/Category/{action}/{id}")
    public String delCategories(@PathVariable(value= "id") int id,
                                @PathVariable(value = "action") String action,
                                Model model){
        Connection connection = con.connect();
        categories = new Categories();
        List<Entity> categoriesList = new ArrayList<>();
        if(action.equals("del")){
            categoriesService.deleteEntity(connection,id);
            categoriesList = categoriesService.showAllEntity(connection);
            model.addAttribute("categories",categoriesList);
        }
        if(action.equals("upDate")){
            categories = (Categories) categoriesService.getEntityID(connection,id);
            categoriesList.add(categories);
            model.addAttribute("categories",categoriesList);
            model.addAttribute("upDate", true);
        }
        con.disconnect();
        return "categoryShow";
    }



    @PostMapping("/admin/updateCategory/{id}")
    public String updateCategory(@PathVariable(value = "id")int id,
                                 @RequestParam(value = "name", defaultValue = "")String name,
                                 @RequestParam(value = "description", defaultValue = "")String description,
                                 @RequestParam(value = "parentId",defaultValue = "0")int parentId,
                                 Model model){
        categories = new Categories();
        Categories categoriesUpDate = new Categories();
        Connection connection = con.connect();
        categories  = (Categories) categoriesService.getEntityID(connection,id);
        if (name.equals("")){
            categoriesUpDate.setName(categories.getName());
        }
        else {
            categoriesUpDate.setName(name);
        }
        if (description.equals("")){
            categoriesUpDate.setDescription(categories.getDescription());
        }
        else {
            categoriesUpDate.setDescription(description);
        }
        if (parentId == 0) {
            categoriesUpDate.setParentId(categories.getParentId());
        }
        else {
            categoriesUpDate.setParentId(parentId);
        }
        categoriesService.setCategory(categoriesUpDate);
        categoriesService.upDateEntity(connection,id);
      //  categoriesUpDate.upDateEntity(connection,id);

        List<Entity> res = categoriesService.showAllEntity(connection);
        model.addAttribute("categories",res);
        con.disconnect();
        return "categoryShow";
    }



}
