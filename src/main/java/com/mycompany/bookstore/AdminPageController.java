package com.mycompany.bookstore;

import Dao.AdminPageDao;
import Dao.BookDao;
import Dao.SignUpDao;
import Entity.Book;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author andrescabrera, yuliiamelnyk
 */

public class AdminPageController implements Initializable {


    //inject  values defined in an FXML file into references

    private byte[] image;

    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<Book, String> ISBN_column;

    @FXML
    private TableColumn<Book, String> Name_column;


    @FXML
    private TextField txt_isbn;

    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_genre;

    @FXML
    private TextField txt_price;

    @FXML
    private TextField txt_description;

    @FXML
    private TextField txt_author;

    @FXML
    private TextField txt_publisher;

    @FXML
    private TextField txt_year;

    @FXML
    private TextField txt_language;

    @FXML
    private Button btn_add;

    @FXML
    private JFXButton btn_image;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private VBox homePage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BookDao dao = new BookDao();
        ObservableList<Book> bookList = null;
        try {
            bookList = FXCollections.observableArrayList(dao.findBooks());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ISBN_column.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
        Name_column.setCellValueFactory(new PropertyValueFactory<Book, String>("Name"));
        bookTable.setItems(bookList);


    }

        @FXML
    public void handleChooseFile(ActionEvent event) throws SQLException, IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.jpg *.png)", "*.jpg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(MainApp.getPrimaryStage());
        image = Files.readAllBytes(file.toPath());

    }

    @FXML
    public void updateBook() {

    }

    @FXML
    public void deleteBook() {

    }

    @FXML
    public void onBackcToHomelicked() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/HomePage.fxml"));
        homePage = loader.load();
        Scene scene = new Scene(homePage);
        scene.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }

    //on Click event method
    @FXML
    protected void handleAddButton(ActionEvent event) throws SQLException {
        Window owner = btn_add.getScene().getWindow();
        if (txt_isbn.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }
        if (txt_name.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email");
            return;
        }

        if (txt_genre.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        if (txt_price.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter an address");
            return;
        }
        if (txt_description.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a phone");
            return;
        }
        if (txt_author.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a phone");
            return;
        }
        if (txt_publisher.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a phone");
            return;
        }
        if (txt_year.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a phone");
            return;
        }
        if (txt_language.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a phone");
            return;
        }
        // Saving our values in local variables

        String isbn = txt_isbn.getText();
        String name = txt_name.getText();
        String genre = txt_genre.getText();
        Float price = Float.parseFloat(txt_price.getText());
        String description = txt_description.getText();
        String author = txt_author.getText();

        String publisher = txt_publisher.getText();
        String year = txt_year.getText();
        String language = txt_language.getText();

        //Create an object Dao and call his method to put in database our local variables

        AdminPageDao adminPageDao = new AdminPageDao();
        adminPageDao.insertBook(isbn, name, genre, price, description, image, author, publisher, year, language);
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Create book",
                "Book created" );
    }

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}