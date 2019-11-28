package com.mycompany.bookstore;

import Dao.LoginDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Entity.Book;
import Entity.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.mycompany.bookstore.MainApp.CurrentUserEmail;

/**
 * @author yuliiamelnyk
 */

public class HomePageController implements Initializable {

    private User user;
    private Book book;
    private List<Book> bookList;
    private int start;

    @FXML
    GridPane gridPane;

    @FXML
    Hyperlink adminPageButton;

    @FXML
    AnchorPane homepage;
    @FXML
    ImageView bookImage;

    @FXML
    VBox vbox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LoginDao loginDao = new LoginDao();
        try {
            //check for email user and if he is Admin, put
            User user = loginDao.getUserByEmail(CurrentUserEmail);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Element.fxml"));
            VBox hb = (VBox) loader.load();

            gridPane.add(hb, 0, 0);

            if (user.isAdmin()) {
                adminPageButton.setVisible(true);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
//Call methods from menu and switch to another scene(page)

    public void gotoHomePage() throws IOException {
        switchScenes("/fxml/HomePage.fxml");
    }

    public void gotoAdminPage() throws IOException {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/fxml/AdminPage.fxml"));
        vbox = loader2.load();
        Scene scene = new Scene(vbox);
        scene.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setScene(scene);
        stage.show();

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
        //gridPane.add();
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Element.fxml"));

        GridPane gp = new GridPane();
        gp.setVgap(300);
        gp.setHgap(200);

        int index = start;
        for (int i = 0; i < bookList.size(); i++) {
            for (int j = 0; j < bookList.size(); j++) {

                if (index >= bookList.size()) {
                    break;
                }
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Element.fxml"));
                    VBox hb = (VBox) loader.load();
                    ElementCardController elementCardController = loader.getController();
                    //elementCardController. (bookList.get(index));
                    //((Text)loader.getNamespace().get("snbi")).setText("kajsgdkjahskd");
                    //((Text)loader.getNamespace().get("snbi")).setText("kajsgdkjahskd");

                    gridPane.add(hb, j, i);
                    index++;

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return gp;
    }

    @FXML
    void gotoBookpage() {
        String bookISBN = book.getISBN();
        MainApp.CurrentBookISBN = bookISBN;
    }


}
