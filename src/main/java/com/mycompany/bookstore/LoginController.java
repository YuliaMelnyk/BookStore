package com.mycompany.bookstore;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Dao.LoginDao;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import Entity.User;

import static com.mycompany.bookstore.MainApp.CurrentUserEmail;
import static javafx.application.Application.getUserAgentStylesheet;

/**
 * @author andrescabrera, yuliiamelnyk
 */
public class LoginController extends BackToHome implements Initializable {


    //inject  values defined in an FXML file into references
    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Button btn_signIn;

    @FXML
    private VBox homePage;

    @FXML
    private VBox vboxRegitration;

    Scene scene2;

    User user;


//styles for info window
    private static final String STYLESHEET = LoginController.class.getResource("/fxml//styles/alert.css").toExternalForm();

    //on Click event method
    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {

        Window owner = btn_signIn.getScene().getWindow();

        System.out.println(txt_email.getText());
        System.out.println(txt_password.getText());

        if (txt_email.getText().isEmpty() & (txt_email.getText().matches("\\b[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}\\b"))) {
            showAlert(AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email correctly");
            return;
        }
        if (txt_password.getText().isEmpty()) {
            showAlert(AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password correctly");
            return;
        }
        // Saving our values in local variables

        String emailId = txt_email.getText();
        String password = txt_password.getText();

        //Create an object Dao and call his method to put in database our local variables

        LoginDao loginDao = new LoginDao();
        user = loginDao.getUserByEmail(emailId);
        String name = user.getName();

        //boolean for validate our values
        boolean flag = loginDao.validate(emailId, password);

        if (!flag) {
            infoBox("Please enter correct Email and Password", null, "Failed");
        } else {

            infoBox("Welcome back " + name, null, "Success!");
            CurrentUserEmail = emailId;
            onBackToHomelicked();

        }
    }
    //method to show info window

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.getDialogPane().getStylesheets().add(STYLESHEET);
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
        txt_password.setPromptText("Enter password");
        txt_email.setPromptText("example@example.com");
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
    }

    @Override
    public void onBackToHomelicked() throws IOException {
        super.onBackToHomelicked();
    }
}
