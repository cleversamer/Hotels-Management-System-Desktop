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
            primaryStage.setScene(new Scene(root, 440, 250));
            primaryStage.setResizable(false);
            primaryStage.setTitle("V1.12 Login");
            primaryStage.getIcons().add(new Image(getClass().getResource("icon.png").toString()));
            stage = primaryStage;
            stage.show();
        } catch (Exception e) {
            System.out.println("Error Run Application");
        }
    }

    @Override
    public void stop() {
        if (Data.thereAreChanges) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Apply changes");
            alert.setHeaderText("Do you want to apply changes?");
            alert.setContentText("Press OK to apply changes.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK)
                Data.exportData();
        }
    }
}
