package com.mycompany.bookstore;

import Dao.BookDao;
import Dao.LoginDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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

import static com.mycompany.bookstore.MainApp.CurrentBookISBN;
import static com.mycompany.bookstore.MainApp.CurrentUserEmail;

/**
 * @author yuliiamelnyk
 */

public class HomePageController implements Initializable {

    private User user;
    private Book book;
    private List<Book> bookList;
    private int start;
    private BookDao bookDao;

    @FXML
    GridPane gridPane;

    @FXML
    Hyperlink adminPageButton;

    @FXML
    ImageView bookImage;

    @FXML
    VBox adminVBox, homePage, cartBox;

    @FXML
    BorderPane borderPaneBook;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LoginDao loginDao = new LoginDao();
        try {
            //check for email user and if he is Admin, put
            User user = loginDao.getUserByEmail(CurrentUserEmail);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Element.fxml"));
            VBox hb = (VBox) loader.load();

            gridPane.add(hb, 0, 0);
            //addCardElement();
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
        adminVBox = loader2.load();
        Scene scene = new Scene(adminVBox);
        scene.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setTitle("Admin Page");
        stage.setScene(scene);
        stage.show();
    }

    public void gotoAccountPage() throws IOException {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/fxml/HomePage.fxml"));
        homePage = loader2.load();
        Scene scene = new Scene(homePage);
        scene.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setTitle("My Account");
        stage.setScene(scene);
        stage.show();
    }

    public void gotoCartPage() throws IOException {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/fxml/Cart.fxml"));
        cartBox = loader2.load();
        Scene scene = new Scene(cartBox);
        scene.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }

    // Create a new scene for a window

    public void switchScenes(String url) throws IOException {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource(url));
        homePage = loader2.load();
        Scene scene = new Scene(homePage);
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
                    //bookList = bookDao.findBooksByISBN(CurrentBookISBN);
                    ((Text) loader.getNamespace().get("name")).setText("name");
                    ((Text) loader.getNamespace().get("image")).setText("image");
                    ((Text) loader.getNamespace().get("price")).setText("price");
                    //ElementCardController elementCardController = loader.getController();
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
    void ImagePressed() {

        String bookISBN = book.getISBN();
        MainApp.CurrentBookISBN = bookISBN;

        try {
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/fxml/Book.fxml"));
            borderPaneBook = loader2.load();
            Scene scene2 = new Scene(borderPaneBook);

            scene2.getStylesheets().add("/fxml/styles/style.css");
            Stage stage = MainApp.getPrimaryStage();
            stage.setScene(scene2);
            stage.show();
            stage.show();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
