package com.mycompany.bookstore;

import Entity.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class BookController implements Initializable {


    @FXML
    public ImageView imageBook;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void addToCart() {

    }

    public void postBook(Book p) {

        for (String image : p.getImages()) {
            System.out.println(image);
        }
    }
}
