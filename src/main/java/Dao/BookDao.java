package Dao;

import Entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao extends Dao {


    // create query
    private static final String SELECT_QUERY_BOOKS = "SELECT * FROM book";
    private PreparedStatement preparedStatement;

    public boolean selectBook() throws SQLException {

        try {
            Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT_QUERY_BOOKS);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return true;
            }
            connection.close();
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    //Selects Books with ISBN
    public List<Book> findBooksByISBN(String ISBN) {
        List<Book> results = null;
        ResultSet resultSet = null;

        try {
            preparedStatement.setString(1, ISBN);

            // executeQuery returns a ResultSet that contains the desired records
            resultSet = preparedStatement.executeQuery();

            //results = new ArrayList<Book>();
            Book book = new Book();
            while(resultSet.next()) {
                book.setISBN((String) resultSet.getObject(1));
                results.add(new Book(
                        resultSet.getString("isbn"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"),
                        resultSet.getFloat("price"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getString("year"),
                        resultSet.getString("language")));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

        return results;
    }

}

