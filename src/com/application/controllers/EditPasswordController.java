package com.application.controllers;

import com.application.Run;
import com.application.model.Data;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;

public class EditPasswordController {

    private static Stage stage;

    @FXML
    PasswordField currentPassword, newPassword1, newPassword2;

    public static void load() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(Run.class.getResource("fxml/EditPasswordWindow.fxml"));
            stage.setScene(new Scene(root, 300, 200));
            stage.setTitle("Edit Password");
            stage.setResizable(false);
            stage.getIcons().add(new Image(Run.class.getResource("icon.png").toString()));
            stage.show();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @FXML
    public void confirm() {
        String currentPassword = this.currentPassword.getText();
        if (!currentPassword.equals(Data.hotel.getPassword())) {
            clearFields();
            return;
        } else if (!this.newPassword1.getText().equals(this.newPassword2.getText())) {
            clearFields();
            return;
        } else if (!Data.hotel.setPassword(this.newPassword1.getText())) {
            AlertsLoader.invalidPassword();
            return;
        }
        Data.thereAreChanges = true;
        stage.close();
    }

    private void clearFields() {
        this.currentPassword.setText("");
        this.newPassword1.setText("");
        this.newPassword2.setText("");
    }
}
