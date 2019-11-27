package com.mycompany.bookstore;

import Dao.LoginDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
    private List<Book> bookList;
    private int start;
    @FXML
    Hyperlink adminPageButton;

    @FXML
    AnchorPane homepage;


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


    }

    public GridPane addCardElement() {
        GridPane gp = new GridPane();
        gp.setVgap(30);
        gp.setHgap(20);

        int index = start;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (index >= bookList.size()) {
                    break;
                }

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Element.fxml"));
                    VBox hb = (VBox) loader.load();
                    ElementCardController elementCardController = loader.getController();
                    //elementCardController. (bookList.get(index));

                    gp.add(hb, j, i);
                    index++;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return gp;
    }
}
