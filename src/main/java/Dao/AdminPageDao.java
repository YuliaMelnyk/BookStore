package Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPageDao {
    private static final String URL = "jdbc:mysql://85.214.120.213:3306/bookstoredam";
    private static final String NAME = "bookstore";
    private static final String PASSWORD = "1dam";

    private static final String QUERY = "INSERT INTO book (isbn , name, genre, price, description, author, publisher, year, language) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


    public void insertBook(String isbn, String name, String genre, String price, String description, String author, String publisher, String year, String language) throws SQLException {

        try (Connection connection = DriverManager
                .getConnection(URL, NAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, genre);
            preparedStatement.setString(4, price);
            preparedStatement.setString(5, description);
            preparedStatement.setString(6, author);
            preparedStatement.setString(7, publisher);
            preparedStatement.setString(8, year);
            preparedStatement.setString(9, language);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        getException(ex);
    }

    static void getException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                Throwable p = ex.getCause();
                while (p != null) {
                    System.out.println(p);
                    p = p.getCause();
                }
            }
        }
    }

}
