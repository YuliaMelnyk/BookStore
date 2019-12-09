package com.mycompany.bookstore;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class BackToHome {
    @FXML
    private VBox homePage;

    @FXML
    VBox LoginVBox;


    public void onBackToHomelicked() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/HomePage.fxml"));
        homePage = loader.load();
        Scene scene = new Scene(homePage);
        scene.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setTitle("Home Page");
        stage.setScene(scene);
        stage.show();
    }

    public void onBackToLoginclicked() throws IOException {
        FXMLLoader loader2 = new FXMLLoader();
        loader2.setLocation(getClass().getResource("/fxml/Login.fxml"));
        LoginVBox = loader2.load();
        Scene scene = new Scene(LoginVBox);
        scene.getStylesheets().add("/fxml/styles/style.css");
        Stage stage = MainApp.getPrimaryStage();
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.show();
    }
}
