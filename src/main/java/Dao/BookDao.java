package Dao;

import Entity.Book;
import Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @author andrescabrera, yuliiamelnyk
 */
public class BookDao extends Dao {


    // create query
    private static final String SELECT_QUERY_BOOKS = "SELECT * FROM book";
    private static final String SELECT_QUERY_BOOK = "SELECT * FROM book WHERE isbn = ?";
    private PreparedStatement preparedStatement;

    //Selects List de books, set ISBN, Name and price
    public ArrayList<Book> findBooks() {
        ArrayList<Book> results = new ArrayList<>();
        ResultSet resultSet = null;

        try {
            Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            preparedStatement = connection.prepareStatement(SELECT_QUERY_BOOKS);
            // executeQuery returns a ResultSet that contains the desired records
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setISBN((String) resultSet.getObject(1));
                book.setName((String) resultSet.getObject(2));
                book.setPrice((Float) resultSet.getObject(4));
                //book.setImage((Byte) resultSet.getObject(6));

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
            return results;
        }
    }
    public Book getBookbyISBN(String isbn) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY_BOOK)) {
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();

            Book book = new Book();
            while (resultSet.next()) {
                book.setName((String) resultSet.getObject(2));
                book.setGenre((String) resultSet.getObject(3));
                book.setPrice((Float) resultSet.getObject(4));
                book.setDescription((String) resultSet.getObject(5));
                //book.setImage((Byte) resultSet.getObject(6));
                book.setAuthor((String) resultSet.getObject(7));
                book.setYear((String) resultSet.getObject(8));
                book.setLanguage((String) resultSet.getObject(9));
            }
            return book;
        }
    }
}

