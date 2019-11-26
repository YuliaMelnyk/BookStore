package com.mycompany.bookstore;

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

    //on Click event method

   /* protected void handleSubmitButtonAction(ActionEvent event) throws SQLException {
        Window owner = btn_signUp.getScene().getWindow();
        if (txt_name.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }
        if (txt_email.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email");
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
        if (txt_phone.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a phone");
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
    }*/
}