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

    private static Stage STAGE;

    @FXML
    PasswordField currentPassword, newPassword1, newPassword2;

    public static void LOAD() {
        try {
            STAGE = new Stage();
            Parent root = FXMLLoader.load(Run.class.getResource("fxml/EditPasswordWindow.fxml"));
            STAGE.setScene(new Scene(root, 300, 200));
            STAGE.setTitle("Edit Password");
            STAGE.setResizable(false);
            STAGE.getIcons().add(new Image(Run.class.getResource("icon.jpg").toString()));
            STAGE.show();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @FXML
    public void confirm() {
        String currentPassword = this.currentPassword.getText();
        if (!currentPassword.equals(Data.HOTEL.getPassword())) {
            clearFields();
            return;
        } else if (!this.newPassword1.getText().equals(this.newPassword2.getText())) {
            clearFields();
            return;
        } else if (!Data.HOTEL.setPassword(this.newPassword1.getText())) {
            AlertsLoader.INVALID_PASSWORD();
            return;
        }
        STAGE.close();
    }

    private void clearFields() {
        this.currentPassword.setText("");
        this.newPassword1.setText("");
        this.newPassword2.setText("");
    }
}
