package Dao;

public class BookDao extends Dao {


    // create query
    private static final String SELECT_QUERY = "SELECT * FROM book WHERE isbn = ?, name = ?, genre = ?, price = ?";
}
