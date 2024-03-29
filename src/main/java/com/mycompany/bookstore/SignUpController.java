package com.mycompany.bookstore;

import Dao.SignUpDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author andrescabrera, yuliiamelnyk
 */

public class SignUpController extends BackToHome implements Initializable {

    private static final String STYLESHEET = LoginController.class.getResource("/fxml//styles/alert.css").toExternalForm();
    @FXML
    private TextField txt_name;

    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_password;

    @FXML
    private TextField txt_address;

    @FXML
    private TextField txt_phone;

    @FXML
    private Button btn_signUp;

    @FXML
    private VBox homePage;

    @FXML
    private ImageView backToLogin;
    @FXML
    private VBox LoginVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_name.setPromptText("Enter username");
        txt_password.setPromptText("Enter password");
        txt_email.setPromptText("example@example.com");
        txt_address.setPromptText("Enter address");
        txt_phone.setPromptText("123456789");
    }

    @FXML
    public void onBackToLoginclicked() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Login.fxml"));
        LoginVBox = loader.load();
        Scene scene = new Scene(LoginVBox);
        scene.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }

    //on Click event method
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) throws SQLException, IOException {
        Window owner = btn_signUp.getScene().getWindow();
        if (txt_name.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }
        if (txt_email.getText().isEmpty() || !(txt_email.getText().matches("\\b[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}\\b"))) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email correctly");
            return;
        }

        if (txt_password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        if (txt_address.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter an address");
            return;
        }
        if (txt_phone.getText().isEmpty() || !(txt_phone.getText().matches("[0-9]+"))) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your phone correctly");
            return;
        }
        // Saving our values in local variables

        String name = txt_name.getText();
        String email = txt_email.getText();
        String password = txt_password.getText();
        String address = txt_address.getText();
        String phone = txt_phone.getText();

        //Create an object Dao and call his method to put in database our local variables

        SignUpDao signUpDao = new SignUpDao();
        signUpDao.insertRecord(name, email, password, address, phone);
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + txt_name.getText());
        onBackToHomelicked();
    }

    @Override
    public void onBackToHomelicked() throws IOException {
        super.onBackToHomelicked();
    }
    //method to show alert window

    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.getDialogPane().getStylesheets().add(STYLESHEET);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
