package com.mycompany.bookstore;

import Dao.LoginDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Entity.Book;
import Entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.mycompany.bookstore.MainApp.CurrentUserEmail;

/**
 * @author yuliiamelnyk
 */

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
            //check for email usser and if he is Admin, put
            User user = loginDao.getUserByEmail(CurrentUserEmail);
            if (user.isAdmin()) {
                adminPageButton.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//Call methods from menu and switch to another scene(page)

    public void gotoHomePage() throws IOException {
        switchScenes("/fxml/HomePage.fxml");
    }

    public void gotoAdminPage() throws IOException {
        switchScenes("/fxml/AdminPage.fxml");

    }

    public void gotoAccountPage() throws IOException {
        switchScenes("/fxml/HomePage.fxml");
    }

    public void gotoCartPage() throws IOException {
        switchScenes("/fxml/HomePage.fxml");
    }

    public void showAdminButton(boolean isAdmin) {
        if (isAdmin) {
            adminPageButton.setVisible(true);
        }
    }

    // Create a new scene for a window

    public void switchScenes(String url) throws IOException {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource(url));
        homepage = loader2.load();
        Scene scene = new Scene(homepage);
        scene.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.show();
        showAdminButton(user.isAdmin());
    }
}
