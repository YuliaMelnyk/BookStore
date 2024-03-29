package com.mycompany.bookstore;

import Dao.AdminPageDao;
import Dao.BookDao;
import Entity.Book;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.mycompany.bookstore.MainApp.BookList;


/**
 * @author andrescabrera, yuliiamelnyk
 */

public class AdminPageController extends BackToHome implements Initializable {

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

    private BookDao dao;
    private Book book;
    //private ObservableList<Book> bookList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dao = new BookDao();
        try {
            book = dao.getBookbyISBN(MainApp.CurrentBookISBN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ISBN_column.setCellValueFactory(new PropertyValueFactory<Book, String>("ISBN"));
        Name_column.setCellValueFactory(new PropertyValueFactory<Book, String>("Name"));
        bookTable.getItems().setAll(BookList);
        bookTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if (mouseEvent.isPrimaryButtonDown() && mouseEvent.getClickCount() == 2) {
                    Book selectedItem = bookTable.getSelectionModel().getSelectedItem();
                    txt_isbn.setText(selectedItem.getISBN());
                    txt_name.setText(selectedItem.getName());
                    txt_genre.setText(selectedItem.getGenre());
                    String price = String.valueOf(selectedItem.getPrice());
                    txt_price.setText(price);
                    txt_description.setText(selectedItem.getDescription());
                    txt_author.setText(selectedItem.getAuthor());
                    txt_publisher.setText(selectedItem.getPublisher());
                    txt_year.setText(selectedItem.getYear());
                    txt_language.setText(selectedItem.getLanguage());
                }
            }
        });

    }

    @Override
    public void onBackToHomelicked() throws IOException {
        super.onBackToHomelicked();
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
       btn_update.setOnAction(e -> {

           String isbn = txt_isbn.getText();
           String name = txt_name.getText();
           String genre = txt_genre.getText();
           Float price = Float.parseFloat(txt_price.getText());
           String description = txt_description.getText();
           String author = txt_author.getText();

           String publisher = txt_publisher.getText();
           String year = txt_year.getText();
           String language = txt_language.getText();

           AdminPageDao adminPageDao = new AdminPageDao();
           try {
               adminPageDao.insertBook(isbn, name, genre, price, description, image, author, publisher, year, language);
           } catch (SQLException ex) {
               ex.printStackTrace();
           }
       });
    }

    @FXML
    public void deleteBook() {
        btn_delete.setOnAction(e -> {
            book = bookTable.getSelectionModel().getSelectedItem();
            bookTable.getItems().remove(book);
            BookList.remove(book);
        });
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
                "Book created");
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