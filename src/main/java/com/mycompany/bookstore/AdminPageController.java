package com.mycompany.bookstore;

import Dao.AdminPageDao;
import Dao.SignUpDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class AdminPageController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    //inject  values defined in an FXML file into references

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
    private TextField txt_image;

    @FXML
    private TextField txt_publisher;

    @FXML
    private TextField txt_year;

    @FXML
    private TextField txt_language;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_delete;

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
        String image = txt_image.getText();
        String publisher = txt_publisher.getText();
        String year = txt_year.getText();
        String language = txt_language.getText();

        //Create an object Dao and call his method to put in database our local variables

        AdminPageDao adminPageDao = new AdminPageDao();
        adminPageDao.insertBook(isbn, name, genre, price, description, author, image, publisher, year, language);
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + txt_name.getText());
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