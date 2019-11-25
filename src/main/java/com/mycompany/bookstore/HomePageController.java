package com.mycompany.bookstore;

import Dao.LoginDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.Book;
import logic.User;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.mycompany.bookstore.MainApp.CurrentUserEmail;

public class HomePageController {

    private User user;
    @FXML
    Hyperlink adminPageButton;

    @FXML
    AnchorPane homepage;
    private List<Book> bookList;

    public void initialize() {
        LoginDao loginDao = new LoginDao();
        try {
            User user = loginDao.getUserByEmail(CurrentUserEmail);
            if(user.isAdmin())
            {
                adminPageButton.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gotoHomePage() throws IOException {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/fxml/HomePage.fxml"));
        homepage = loader2.load();
        Scene scene = new Scene(homepage);
        scene.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.show();
        showAdminButton(user.isAdmin());
    }

    public void gotoAdminPage() {

    }

    public void gotoAccountPage() {

    }

    public void gotoCartPage() {

    }

    public void showAdminButton(boolean isAdmin) {
        if (isAdmin) {
            adminPageButton.setVisible(true);
        }
    }
}
