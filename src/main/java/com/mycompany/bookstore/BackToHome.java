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
}
