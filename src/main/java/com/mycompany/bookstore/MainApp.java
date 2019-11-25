package com.mycompany.bookstore;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


public class MainApp extends Application {

    private static Stage pStage;
@FXML
    JFXButton btn_signUp;

@FXML
        VBox vboxRegitration;



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
        stage.setScene(scene);
        stage.show();

/*        btn_signUp.setOnAction(e -> stage.setScene(scene2));
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/fxml/SignUp.fxml"));
        vboxRegitration = loader2.load();
        scene2 = new Scene(vboxRegitration);
        scene2.getStylesheets().add("/fxml/styles/style.css");*/
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
        launch(args);
    }

}
