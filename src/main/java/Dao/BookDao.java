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
    public ArrayList<Book> findBooks() {
        ArrayList<Book> results = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT_QUERY_BOOKS);
            // executeQuery returns a ResultSet that contains the desired records
            resultSet = preparedStatement.executeQuery();

            //results = new ArrayList<Book>();

            while(resultSet.next()) {
                Book book = new Book();
                book.setISBN((String) resultSet.getObject(1));
                book.setName((String) resultSet.getObject(2));
                results.add(book);
                /*results.add(new Book(
                        resultSet.getString("isbn"),
                        resultSet.getString("name"),
                        resultSet.getString("genre"),
                        resultSet.getFloat("price"),
                        resultSet.getString("description"),
                        resultSet.getString("image"),
                        resultSet.getString("author"),
                        resultSet.getString("publisher"),
                        resultSet.getString("year"),
                        resultSet.getString("language")));*/
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

            return results;
        }
    }
}

