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
 * @author andrescabrera, yuliiamelnyk
 */

public class HomePageController implements Initializable {

    private User user;
    private Book currentbook;
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

    @FXML Label isbn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        LoginDao loginDao = new LoginDao();
        try {
            //check for email user and if he is Admin, put
            User user = loginDao.getUserByEmail(CurrentUserEmail);

            addCardElements();
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

    //method using foreach to take element book and fill fxml element in positions in gridpane
    public void addCardElements() throws IOException {

        GridPane gp = new GridPane();
        gp.setVgap(300);
        gp.setHgap(200);

        int index = -1;
        bookDao = new BookDao();
        bookList = bookDao.findBooks();

        for (Book book : bookList) {
            index++;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Element.fxml"));
            VBox hb = (VBox) loader.load();
            hb.setMinHeight(500);


            String currentISBN = book.getISBN();
            CurrentBookISBN = currentISBN;
            ((Label) loader.getNamespace().get("isbn")).setText(currentISBN);

            //set isbn
            //TODO
            //put name value en label from DB
            ((Label) loader.getNamespace().get("name")).setText(book.getName());
            //Convert price en String
            String price = String.valueOf(book.getPrice());
            //put price value en label from DB
            ((Label) loader.getNamespace().get("price")).setText(price);

            //add each element in gridpane
            gridPane.add(hb, 0, index);
        }
    }
    //When the costumer pressed in the image -> enter in the page Book.fxml
/*    @FXML
    void ImagePressed() {
        MainApp.CurrentBookISBN = currentISBN.getText();

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
    }*/

}
