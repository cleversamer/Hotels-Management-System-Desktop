package com.application;

import com.application.controllers.LoginController;
import com.application.model.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Run extends Application {

    public static Stage stage;

    @Override
    public void init() {
        Data.importData();
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(LoginController.LOCATION);
            primaryStage.setScene(new Scene(root, 400, 250));
            primaryStage.setResizable(false);
            primaryStage.setTitle("Login");
            primaryStage.getIcons().add(new Image(getClass().getResource("icon.jpg").toString()));
            stage = primaryStage;
            stage.show();
        } catch (Exception e) {
            System.out.println("Error Run Application");
        }
    }

    @Override
    public void stop() {
        Data.exportData();
    }
}
