package project.controller;

import project.dao.DaoConnection;
import project.entities.Entity;
import project.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.ProductServiceImp;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerProduct {
    Product product;
    private final DaoConnection con;
    private ProductServiceImp productService;

    @Autowired
    public ControllerProduct(DaoConnection con, ProductServiceImp productService) {

        this.con = con;
        this.productService = productService;
    }

    @GetMapping("/admin/productShow")
    public String getProductShow(Model model) {
        Connection connection = con.connect();
       //product = new Product();
        List<Entity> productsList = productService.showAllEntity(connection);
        model.addAttribute("products", productsList);
        con.disconnect();
        return "productsShow";
    }

    /**
     * Метод добавления сущностей в таблицу BOOK_PRODUCT
     * Приходят из формы "productsShow.jsp"
     * @param name название
     * @param description описание продукта
     * @param price стоимость
     * @param isActive маркер:активная позиция - "1",не активная - "0" (не активные позиции не отображаются в просмотре)
     * @param categoryId
     * @param model
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
        Connection connection = con.connect();
        productService.addEntity(connection);
        List<Entity> productsList = productService.showAllEntity(connection);
        con.disconnect();
        model.addAttribute("products",productsList);
        return "productsShow";
    }

    /** - Метод удаления сущности Product в таблице BOOK_PRODUCT.
     *  - Достает запись из базы данных для редактирования и отправляет
     * на страницу "productsShow.jsp" с параметром  ("upDate", true)
     * для подготовки формы на редактирование.
     * @param id
     * @param action
     * @param model
     * @return
     */
    @GetMapping("/admin/Product/{action}/{id}")
    public String delProduct(@PathVariable(value= "id") int id,
                             @PathVariable(value = "action") String action,
                                Model model){
        Connection connection = con.connect();
        product = new Product();
        List<Entity> productsList = new ArrayList<>();
        if(action.equals("del")){
            productService.deleteEntity(connection,id);
            productsList = productService.showAllEntity(connection);
            model.addAttribute("products",productsList);
        }
        if(action.equals("upDate")){
            product = (Product) productService.getEntityID(connection,id);
            productsList.add(product);
            model.addAttribute("products",productsList);
            model.addAttribute("upDate", true);
        }
        con.disconnect();
        return "productsShow";
    }

    /**
     * Метод получает данные из формы "productsShow.jsp" и вносит изменения по ID в
     * таблице BOOK_PRODUCT
     * @param id
     * @param name
     * @param description
     * @param price
     * @param is_active
     * @param categoryId
     * @param model
     * @return
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
        Connection connection = con.connect();
        product  = (Product) productService.getEntityID(connection,id);
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
        productService.upDateEntity(connection,id);

        List<Entity> rezult = productService.showAllEntity(connection);
        model.addAttribute("products",rezult);
        con.disconnect();
        return "productsShow";
    }

}
