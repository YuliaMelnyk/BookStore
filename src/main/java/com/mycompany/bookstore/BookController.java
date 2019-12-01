package com.mycompany.bookstore;

import Dao.BookDao;
import Entity.Book;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author andrescabrera, yuliiamelnyk
 */
public class BookController implements Initializable {

    private Book book;
    private List<Book> bookList;
    BookDao bookDao;

    @FXML
    public ImageView imageBook;
    @FXML
    public JFXButton cartButton;

    @FXML
    public Label priceBook, nameBook, descripcionBook;

    @FXML
    private VBox vBox;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookDao = new BookDao();
        try {
            book = bookDao.getBookbyISBN(MainApp.CurrentBookISBN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nameBook.setText(book.getName());
                //Convert price en String
                String price = String.valueOf(book.getPrice());
                //put price value en label from DB
                priceBook.setText(price);
    }

    //method to add book in the cart from onCklick in the button
    public void addToCart() {

    }

}
