package project.dao;

public class ConstSQLTable {
    //CATEGORIES_TABLE
    public final static String ADD_CATEGORIES = "INSERT INTO BOOK_CATEGORIES(name, description, parent_id)values (?,?,?)";
    public final static String DELETE_CATEGORIES_ID = "DELETE FROM BOOK_CATEGORIES WHERE CATEGORY_ID = ?";
    public final static String SHOW_ALL_CATEGORIES = "SELECT * FROM BOOK_CATEGORIES WHERE CATEGORY_ID > 1";
    public final static String SHOW_CATEGORIES_ID = "SELECT * FROM BOOK_CATEGORIES WHERE CATEGORY_ID = ?";
    public final static String UPDATE_CATEGORY_ID = "UPDATE BOOK_CATEGORIES SET NAME = ?,DESCRIPTION = ?,PARENT_ID = ? WHERE CATEGORY_ID = ?";
    public final static String SHOW_CATEGORIES_BY_PARENT = "SELECT * FROM BOOK_CATEGORIES WHERE PARENT_ID = ? AND CATEGORY_ID > 1";
    //PRODUCT_TABLE
    public final static String SHOW_ALL_PRODUCTS = "SELECT * FROM BOOK_PRODUCT";
    public final static String SHOW_PRODUCTS_BY_CATEGORY_ID = "SELECT * FROM BOOK_PRODUCT WHERE CATEGORY_ID = ?";
    public final static String ADD_PRODUCT = "INSERT INTO BOOK_PRODUCT(name, description, price, is_active, category_id)values (?,?,?,?,?)";
    public final static String DELETE_PRODUCT_ID = "DELETE FROM BOOK_PRODUCT WHERE PRODUCT_ID = ?";
    public final static String SHOW_PRODUCT_ID = "SELECT * FROM BOOK_PRODUCT WHERE PRODUCT_ID = ?";
    public final static String UPDATE_PRODUCT_ID = "UPDATE BOOK_PRODUCT SET NAME = ?,DESCRIPTION = ?,PRICE = ?,IS_ACTIVE = ?,CATEGORY_ID = ? WHERE PRODUCT_ID = ?";

    public final static String SHOW_ALL_ACCOUNTS = "SELECT * FROM BOOK_LOGIN";
    public final static String SELECT_LOGIN_PASSWORD_ROLE = "SELECT ID, LOGIN, PASSWORD,ROLE from BOOK_LOGIN WHERE LOGIN=?";
    public final static String SELECT_LOGIN_PASSWORD = "SELECT LOGIN, '{noop}'||PASSWORD, 'true' from BOOK_LOGIN WHERE LOGIN=?";
    public final static String SELECT_LOGIN_ROLE = "SELECT LOGIN, ROLE BOOK_LOGIN WHERE LOGIN=?";
}
