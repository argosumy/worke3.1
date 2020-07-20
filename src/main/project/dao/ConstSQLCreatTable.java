package project.dao;

public class ConstSQLCreatTable {
    //CATEGORIES
    public final static  String ADD_CATEGORIES = "INSERT INTO BOOK_CATEGORIES(name, description, parent_id)values (?,?,?)";


    final static String DELETE_CATEGORIES_ID = "DELETE FROM BOOK_CATEGORIES WHERE ID = ?";
    final static String DELETE_CATEGORIES_NAME = "DELETE FROM BOOK_CATEGORIES WHERE NAME = ?";
}
