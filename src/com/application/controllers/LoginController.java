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

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class LoginController {

    public static final URL LOCATION = Run.class.getResource("fxml/LoginWindow.fxml");

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    public static void load() {
        try {
            Parent root = FXMLLoader.load(LOCATION);
            Run.stage.setScene(new Scene(root, 400, 250));
            Run.stage.setTitle("Login");
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @FXML
    public void login() {
        Hotel current = Data.getHotel(this.username.getText(), this.password.getText());
        if (current == null) {
            AlertsLoader.invalidLoginInfo();
            this.username.setText("");
            this.password.setText("");
            return;
        }
        Data.hotel = current;
        MainController.load();
    }

    @FXML
    public void signup() {
        SignUpController.load();
    }

    @FXML
    public void handleTwitterHyperlink() {
        try {
            Desktop.getDesktop().browse(new URI("https://twitter.com/ssadawi__"));
        } catch (URISyntaxException | IOException ignored) {
        }
    }

    @FXML
    public void handleGithubHyperlink() {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/ssadawi"));
        } catch (URISyntaxException | IOException ignored) {
        }
    }
}
