package Dao;

import Entity.Book;

import java.sql.*;
import java.util.ArrayList;


/**
 * @author andrescabrera, yuliiamelnyk
 */
public class BookDao extends Dao {

    // create query
    private static final String SELECT_QUERY_BOOKS = "SELECT * FROM book";
    private static final String SELECT_QUERY_BOOK = "SELECT * FROM book WHERE isbn = ?";
    private PreparedStatement preparedStatement;

    //Selects List de books, set ISBN, Name and price
    public ArrayList<Book> findBooks() throws SQLException {
        ArrayList<Book> results = new ArrayList<>();
        ResultSet resultSet = null;
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        try {

            preparedStatement = connection.prepareStatement(SELECT_QUERY_BOOKS);
            // executeQuery returns a ResultSet that contains the desired records
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setISBN((String) resultSet.getObject(1));
                book.setName((String) resultSet.getObject(2));
                book.setPrice((Float) resultSet.getObject(4));
                book.setImage(resultSet.getBytes(6));

                results.add(book);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            connection.close();
            return results;
        }
    }
    public Book getBookbyISBN(String isbn) throws SQLException {
        Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_BOOK)) {
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            Book book = new Book();
            while (resultSet.next()) {
                book.setName((String) resultSet.getObject(2));
                book.setGenre((String) resultSet.getObject(3));
                book.setPrice((Float) resultSet.getObject(4));
                book.setDescription((String) resultSet.getObject(5));
                book.setImage(resultSet.getBytes(6));
                book.setAuthor((String) resultSet.getObject(7));
                book.setPublisher((String) resultSet.getObject(9));
                book.setYear((String) resultSet.getObject(9));
                book.setLanguage((String) resultSet.getObject(10));
            }
            return book;
        } finally {
            connection.close();
        }
    }
}

