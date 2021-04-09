package com.application.controllers;

import com.application.model.Data;
import com.application.model.Hotel;
import com.application.Run;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginController {

    public static final URL LOCATION = Run.class.getResource("fxml/LoginWindow.fxml");

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    public static void LOAD() {
        try {
            Parent root = FXMLLoader.load(LOCATION);
            Run.currentStage.setScene(new Scene(root, 400, 250));
            Run.currentStage.setTitle("Login");
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @FXML
    public void login() {
        String username = this.username.getText();
        String password = this.password.getText();
        Hotel current = Data.GET_HOTEL(username, password);
        if (current == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username or password");
            alert.setContentText("Please enter a valid username or password");
            alert.show();
            return;
        }
        clearNodes();
        Data.CURRENT_HOTEL = current;
        MainController.LOAD();
    }

    @FXML
    public void signup() {
        SignUpController.LOAD();
    }

    private void clearNodes() {
        this.username.setText("");
        this.password.setText("");
    }
}
