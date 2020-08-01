package project.dao;

public class ConstSQLTable {
    //CATEGORIES_TABLE
    public final static String ADD_CATEGORIES = "INSERT INTO BOOK_CATEGORIES(name, description, parent_id)values (?,?,?)";
    public final static String DELETE_CATEGORIES_ID = "DELETE FROM BOOK_CATEGORIES WHERE CATEGORY_ID = ?";
    public final static String DELETE_CATEGORIES_NAME = "DELETE FROM BOOK_CATEGORIES WHERE NAME = ?";
    public final static String SHOW_ALL_CATEGORIES = "SELECT * FROM BOOK_CATEGORIES";
    public final static String SHOW_CATEGORIES_ID = "SELECT * FROM BOOK_CATEGORIES WHERE CATEGORY_ID = ?";
    public final static String UPDATE_CATEGORY_ID = "UPDATE BOOK_CATEGORIES SET NAME = ?,DESCRIPTION = ?,PARENT_ID = ? WHERE CATEGORY_ID = ?";
    //PRODUCT_TABLE
    public final static String SHOW_ALL_PRODUCTS = "SELECT * FROM BOOK_PRODUCT";
    public final static String ADD_PRODUCT = "INSERT INTO BOOK_PRODUCT(name, description, price, is_active, category_id)values (?,?,?,?,?)";
    public final static String DELETE_PRODUCT_ID = "DELETE FROM BOOK_PRODUCT WHERE PRODUCT_ID = ?";
    public final static String SHOW_PRODUCT_ID = "SELECT * FROM BOOK_PRODUCT WHERE CATEGORY_ID = ?";
}
