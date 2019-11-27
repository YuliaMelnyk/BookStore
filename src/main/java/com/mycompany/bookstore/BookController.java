package com.mycompany.bookstore;

import Entity.Book;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author yuliiamelnyk
 */
public class BookController implements Initializable {

    private Book currentBook;

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

    }

    //mothod to add book in the cart from onCklick in the button
    public void addToCart() {

    }

    public void postBook(Book p) {

        for (String image : p.getImages()) {
            System.out.println(image);
        }
    }
}
