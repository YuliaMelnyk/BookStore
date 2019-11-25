package com.mycompany.bookstore;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Dao.LoginDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import logic.User;

import static com.mycompany.bookstore.MainApp.CurrentUserEmail;

/**
 * @author andres
 */
public class LoginController implements Initializable {


    //inject  values defined in an FXML file into references
    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Button btn_signIn;


    @FXML
    AnchorPane homepage;


    @FXML
    private VBox vboxRegitration;

    Scene scene2;

    User user;

    //on Click event method
    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {

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
            //infoBox("Login Successful!", null, "Success");
            CurrentUserEmail = emailId;
            onSignInClick();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // method to show a registration form (onclick button SignUp)
    public void onSignUpClick(ActionEvent event) throws IOException {

        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/fxml/SignUp.fxml"));
        vboxRegitration = loader2.load();
        scene2 = new Scene(vboxRegitration);
        scene2.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene2);
        stage.show();
        stage.show();
    }

    public void onSignInClick() throws IOException {


        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/fxml/HomePage.fxml"));
        homepage = loader2.load();
        Scene scene = new Scene(homepage);
        scene.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.show();
    }
}
