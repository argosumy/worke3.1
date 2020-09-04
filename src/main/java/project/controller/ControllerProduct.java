package main.java.project.controller;

import main.java.project.entities.Entity;
import main.java.project.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import main.java.project.service.ProductServiceImp;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerProduct {
    private Product product;
    private ProductServiceImp productService;

    @Autowired
    public ControllerProduct(ProductServiceImp productService) {

        this.productService = productService;
    }

    @GetMapping("/admin/productShow")
    public String getProductShow(Model model) {
       //product = new Product();
        List<Entity> productsList = productService.showAllEntity();
        model.addAttribute("products", productsList);
        return "productsShow";
    }

    /**
     * Метод добавления сущностей в таблицу BOOK_PRODUCT
     * Приходят из формы "productsShow.jsp"
     * @param name название
     * @param description описание продукта
     * @param price стоимость
     * @param isActive маркер:активная позиция - "1",не активная - "0" (не активные позиции не отображаются в просмотре)
     * @return возвращает "productList" текущий список продуктов с учетом добавленного
     */
    @PostMapping("/admin/addProduct")
    public String addCategories(@RequestParam(value = "name")String name,
                                @RequestParam(value = "description",defaultValue = "")String description,
                                @RequestParam(value = "price", defaultValue = "0")float price,
                                @RequestParam(value = "isActive", defaultValue = "0")int isActive,
                                @RequestParam(value = "categoryId",defaultValue = "1")int categoryId,
                                Model model){
        product = new Product(name,description,price,isActive,categoryId);
        productService.setProduct(product);
        productService.addEntity();
        List<Entity> productsList = productService.showAllEntity();
        model.addAttribute("products",productsList);
        return "productsShow";
    }

    /** - Метод удаления сущности Product в таблице BOOK_PRODUCT.
     *  - Достает запись из базы данных для редактирования и отправляет
     * на страницу "productsShow.jsp" с параметром  ("upDate", true)
     * для подготовки формы на редактирование.
     */
    @GetMapping("/admin/Product/{action}/{id}")
    public String delProduct(@PathVariable(value= "id") int id,
                             @PathVariable(value = "action") String action,
                                Model model){
        product = new Product();
        List<Entity> productsList = new ArrayList<>();
        if(action.equals("del")){
            productService.deleteEntity(id);
            productsList = productService.showAllEntity();
            model.addAttribute("products",productsList);
        }
        if(action.equals("upDate")){
            product = (Product) productService.getEntityID(id);
            productsList.add(product);
            model.addAttribute("products",productsList);
            model.addAttribute("upDate", true);
        }
        return "productsShow";
    }

    /**
     * Метод получает данные из формы "productsShow.jsp" и вносит изменения по ID в
     * таблице BOOK_PRODUCT
     */
    @PostMapping("/admin/productUpDate/{id}")
    public String upDateProduct(@PathVariable(value = "id")int id,
                                @RequestParam(value = "name",defaultValue = "")String name,
                                @RequestParam(value = "description",defaultValue = "")String description,
                                @RequestParam(value = "price",defaultValue = "0")float price,
                                @RequestParam(value = "isActive",defaultValue = "-1")int is_active,
                                @RequestParam(value = "category_id",defaultValue = "0")int categoryId,
                                Model model
                                ){
        product = new Product();
        Product productUpDate = new Product();
        product  = (Product) productService.getEntityID(id);
        if (name.equals("")){
            productUpDate.setName(product.getName());
        }
        else {
            productUpDate.setName(name);
        }
        if (description.equals("")){
            productUpDate.setDescription(product.getDescription());
        }
        else {
            productUpDate.setDescription(description);
        }
        if (price == 0) {
            productUpDate.setPrice(product.getPrice());
        }
        else {
            productUpDate.setPrice(price);
        }
        if (is_active == -1){
            productUpDate.setActive(product.getIsActive());
        }
        else {
            productUpDate.setActive(is_active);
        }
        if (categoryId == 0){
            productUpDate.setCategoryId(product.getCategoryId());
        }
        else {
            productUpDate.setCategoryId(categoryId);
        }
        productService.setProduct(productUpDate);
        productService.upDateEntity(id);

        List<Entity> rezult = productService.showAllEntity();
        model.addAttribute("products",rezult);
        return "productsShow";
    }

}
