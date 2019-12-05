package Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author andrescabrera, yuliiamelnyk
 */


public class AdminPageDao extends Dao {


    private static final String QUERY = "INSERT INTO book (isbn , name, genre, price, description, image, author, publisher, year, language) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";






    public void insertBook(String isbn, String name, String genre, float price, String description, byte[] image, String author, String publisher, String year, String language) throws SQLException {


        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, isbn);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, genre);
            preparedStatement.setFloat(4, price);
            preparedStatement.setString(5, description);
            preparedStatement.setBytes(6, image);
            preparedStatement.setString(7, author);
            preparedStatement.setString(8, publisher);
            preparedStatement.setString(9, year);
            preparedStatement.setString(10, language);


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            printSQLException(e);
        } finally {
            connection.close();
        }
    }
}





