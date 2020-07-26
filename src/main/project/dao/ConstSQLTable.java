package project.dao;

public class ConstSQLTable {
    //CATEGORIES
    public final static  String ADD_CATEGORIES = "INSERT INTO BOOK_CATEGORIES(name, description, parent_id)values (?,?,?)";
    public final static String DELETE_CATEGORIES_ID = "DELETE FROM BOOK_CATEGORIES WHERE CATEGORY_ID = ?";
    public final static String DELETE_CATEGORIES_NAME = "DELETE FROM BOOK_CATEGORIES WHERE NAME = ?";
    public final static String SHOW_ALL_CATEGORIES = "SELECT * FROM BOOK_CATEGORIES";
    public final static String SHOW_CATEGORIES_ID = "SELECT * FROM BOOK_CATEGORIES WHERE CATEGORY_ID = ?";

}
