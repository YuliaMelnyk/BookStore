package com.mycompany.bookstore;

import Entity.Book;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrescabrera, yuliiamelnyk
 */
public class MainApp extends Application {
    public static String CurrentUserEmail;
    public static String CurrentBookISBN;
    public static ArrayList<Book> CartBookList = new ArrayList<Book>();
    public static List<Book> BookList = new ArrayList<Book>();

    private static Stage pStage;

    //start application with login form

    @Override
    public void start(Stage stage) throws Exception {
        setPrimaryStage(stage);
        pStage = stage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Login.fxml"));
        Parent content = loader.load();

        Scene scene = new Scene(content);
        scene.getStylesheets().add("/fxml/styles/style.css");
        
        stage.setTitle("Login Form");
        stage.getIcons().add(new Image("/fxml/images/icons8-book-50.png"));
        stage.setScene(scene);
        stage.show();

    }
    private void setPrimaryStage(Stage pStage){
        MainApp.pStage = pStage;
    }
    public  static Stage getPrimaryStage(){
        return pStage;
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

/*        try{
            URL iconURL = MainApp.class.getResource("/fxml/images/icons8-book-50.png");
            Image image = new ImageIcon(iconURL).getImage();
            com.apple.eawt.Application.getApplication(image);

        } catch (Exception e){
            //for mac OS/ dont work
        }*/
        launch(args);
    }

}
