package main.java.project.controller;

import main.java.project.entities.Categories;
import main.java.project.entities.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import main.java.project.service.CategoriesServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerCategories {
    private Categories categories;
    private CategoriesServiceImp categoriesService;


    @Autowired
    public ControllerCategories(CategoriesServiceImp categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping("/admin/categoryShow")
    public String getCategoriesShow(Model model){
        List<Entity> categoriesList = categoriesService.showAllEntity();
        model.addAttribute("categories",categoriesList);
        return "categoryShow";
    }

    /**
     * Метод добавления новой строки в таблицу BOOK_CATEGORIES
     * @param name Название категории
     * @param description Описание категории
     * @param id ID родительской категории
     * @param model categories - обновленный список всех категории после добавления новой
     */
    @PostMapping ("/admin/addCategories")
    public String addCategories(@RequestParam(value = "name")String name,
                                @RequestParam(value = "description")String description,
                                @RequestParam(value = "parentId", defaultValue = "1")int id, Model model){
        categories = new Categories(name,description,id);
        categoriesService.setCategory(categories);
        categoriesService.addEntity();
        List<Entity> categoriesList = categoriesService.showAllEntity();
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
        categories = new Categories();
        List<Entity> categoriesList = new ArrayList<>();
        if(action.equals("del")){
            categoriesService.deleteEntity(id);
            categoriesList = categoriesService.showAllEntity();
            model.addAttribute("categories",categoriesList);
        }
        if(action.equals("upDate")){
            categories = (Categories) categoriesService.getEntityID(id);
            categoriesList.add(categories);
            model.addAttribute("categories",categoriesList);
            model.addAttribute("upDate", true);
        }
        return "categoryShow";
    }



    @PostMapping("/admin/updateCategory/{id}")
    public String updateCategory(@PathVariable(value = "id")int id,
                                 @RequestParam(value = "name", defaultValue = "")String name,
                                 @RequestParam(value = "description", defaultValue = "")String description,
                                 @RequestParam(value = "parentId",defaultValue = "0")int parentId,
                                 Model model){
        Categories categoriesUpDate = new Categories();
        categories  = (Categories) categoriesService.getEntityID(id);
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
        categoriesService.upDateEntity(id);

        List<Entity> res = categoriesService.showAllEntity();
        model.addAttribute("categories",res);
        return "categoryShow";
    }



}
