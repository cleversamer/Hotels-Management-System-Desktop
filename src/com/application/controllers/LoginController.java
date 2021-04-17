package com.application.controllers;

import com.application.Run;
import com.application.model.Data;
import com.application.model.Hotel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

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
            Run.STAGE.setScene(new Scene(root, 400, 250));
            Run.STAGE.setTitle("Login");
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @FXML
    public void login() {
        Hotel current = Data.GET_HOTEL(this.username.getText(), this.password.getText());
        if (current == null) {
            AlertsLoader.INVALID_LOGIN_INFO();
            this.username.setText("");
            this.password.setText("");
            return;
        }
        Data.HOTEL = current;
        MainController.LOAD();
    }

    @FXML
    public void signup() {
        SignUpController.LOAD();
    }
}
