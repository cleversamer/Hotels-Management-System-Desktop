package com.application;

import com.application.controllers.LoginController;
import com.application.model.Data;
import com.application.model.Hotel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Run extends Application {

    public static Stage currentStage;

    @Override
    public void init() {
        Data.HOTELS.add(new Hotel("Admin", "admin", 100));
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(LoginController.LOCATION);
            primaryStage.setScene(new Scene(root, 400, 250));
            primaryStage.setResizable(false);
            primaryStage.setTitle("Login");
            currentStage = primaryStage;
            currentStage.show();
        } catch (Exception e) {
            System.out.println("Error Run Application");
        }
    }
}
