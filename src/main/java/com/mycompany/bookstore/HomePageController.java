package com.mycompany.bookstore;

import Dao.BookDao;
import Dao.LoginDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Entity.Book;
import Entity.User;
import org.apache.commons.compress.utils.ByteUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import static com.mycompany.bookstore.MainApp.CurrentBookISBN;
import static com.mycompany.bookstore.MainApp.CurrentUserEmail;

/**
 * @author andrescabrera, yuliiamelnyk
 */

public class HomePageController extends BackToHome implements Initializable {


    private List<Book> bookList;
    private BookDao bookDao;

    @FXML
    GridPane gridPane;

    @FXML
    Hyperlink adminPageButton;

    @FXML
    VBox adminVBox, homePage, cartBox;


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

    @Override
    public void onBackToHomelicked() throws IOException {
        super.onBackToHomelicked();
    }

    @Override
    public void onBackToLoginclicked() throws IOException {
        super.onBackToLoginclicked();
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

    //method using foreach to take element book and fill fxml element in positions in gridpane
    public void addCardElements() throws IOException, SQLException {

        GridPane gp = new GridPane();
        gp.setVgap(200);
        gp.setHgap(200);

        int index = -1;
        bookDao = new BookDao();
        bookList = bookDao.findBooks();

        for (Book book : bookList) {
            index++;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Element.fxml"));
            VBox hb = (VBox) loader.load();
            hb.setMinHeight(350);

            //set isbn
            String currentISBN = book.getISBN();
            CurrentBookISBN = currentISBN;
            ((Label) loader.getNamespace().get("isbn")).setText(currentISBN);
            //put image value en label from DB
            if (book.getImage() != null) {
                Image image = new Image(new ByteArrayInputStream(book.getImage()));
                ((ImageView) loader.getNamespace().get("bookImage")).setImage(image);
            }
            //put name value en label from DB
            ((Label) loader.getNamespace().get("name")).setText(book.getName());

            //add each element in gridpane
            gridPane.add(hb, index % 2, index/2);
        }
    }
}
