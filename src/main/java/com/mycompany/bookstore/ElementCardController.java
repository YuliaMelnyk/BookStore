package com.mycompany.bookstore;

import Dao.BookDao;
import Entity.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.mycompany.bookstore.MainApp.CurrentUserEmail;

/**
 * @author andrescabrera, yuliiamelnyk
 */

public class ElementCardController implements Initializable {


    BorderPane borderPaneBook;

    @FXML Label isbn;

    public Book book;


    //When the costumer pressed in the image -> enter in the page Book.fxml
    @FXML
    void ImagePressed() {
        MainApp.CurrentBookISBN = isbn.getText();

        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/fxml/Book.fxml"));
            borderPaneBook = loader2.load();
            Scene scene2 = new Scene(borderPaneBook);

            scene2.getStylesheets().add("/fxml/styles/style.css");
            Stage stage = MainApp.getPrimaryStage();
            stage.setScene(scene2);
            stage.show();
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        BookDao bookDao = new BookDao();
        try {
            book = bookDao.getBookbyISBN(CurrentUserEmail);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

