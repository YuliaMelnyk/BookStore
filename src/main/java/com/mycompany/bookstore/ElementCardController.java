package com.mycompany.bookstore;

import Entity.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ElementCardController implements Initializable {


    BorderPane borderPaneBook;

    @FXML
    private Label name;

    @FXML
    private ImageView bookImage;

    @FXML
    private Label price;

    public Book book;


    public void addCardElement(Book p) {

        book = p;
        bookImage.setImage(new Image(book.getImages().get(1)));
        name.setText(book.getName());
        price.setText(book.getPrice() + " €");
    }


    @FXML
    void ImagePressed() {
        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/fxml/Book.fxml"));
            borderPaneBook = loader2.load();
            Scene scene2 = new Scene(borderPaneBook);


            BookController pc = loader2.getController();
            pc.postBook(book);

            scene2.getStylesheets().add("/fxml/styles/style.css");
            Stage stage = MainApp.getPrimaryStage();
            stage.setScene(scene2);
            stage.show();
            stage.show();
             /*   MainApp.scene2.setRoot(root);
                Main.window.show();*/

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

