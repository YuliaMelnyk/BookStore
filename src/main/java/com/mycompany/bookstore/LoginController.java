package com.mycompany.bookstore;

import java.io.IOException;
import java.sql.SQLException;

import Dao.LoginDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author andres
 */
public class LoginController {

    //inject  values defined in an FXML file into references
    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Button btn_signIn;

    //on Click event method
    @FXML
    public void login(ActionEvent event) throws SQLException {

        Window owner = btn_signIn.getScene().getWindow();

        System.out.println(txt_email.getText());
        System.out.println(txt_password.getText());

        if (txt_email.getText().isEmpty()) {
            showAlert(AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email");
            return;
        }
        if (txt_password.getText().isEmpty()) {
            showAlert(AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }
        // Saving our values in local variables

        String emailId = txt_email.getText();
        String password = txt_password.getText();

        //Create an object Dao and call his method to put in database our local variables

        LoginDao loginDao = new LoginDao();

        //boolean for validate our values
        boolean flag = loginDao.validate(emailId, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {
            infoBox("Login Successful!", null, "Success");
        }
    }
    //method to show info window

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
    //method to show alert window

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    // method to show a registration form (onclick button SignUp)
    public void onSignUpClick(ActionEvent event) throws IOException
    {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/SignUp.fxml"));
        stage.setTitle("Registration Form");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
