package com.application.controllers;

import com.application.Run;
import com.application.model.Data;
import com.application.model.Hotel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController {

    public static final URL LOCATION = Run.class.getResource("fxml/SignUpWindow.fxml");

    @FXML
    TextField username, rooms;

    @FXML
    PasswordField password;

    public static void load() {
        try {
            Parent root = FXMLLoader.load(LOCATION);
            Run.stage.setScene(new Scene(root, 400, 250));
            Run.stage.setTitle("V1.12 Sign Up");
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @FXML
    public void signup() {
        String username = this.username.getText();
        String password = this.password.getText();
        int rooms;
        try {
            rooms = Integer.parseInt(this.rooms.getText());
        } catch (NumberFormatException e) {
            this.rooms.setText("");
            return;
        }
        if (rooms < 1 || rooms > 500) {
            this.rooms.setText("");
            return;
        }
        if (!validAccountInfo(username, password)) {
            AlertsLoader.invalidSignupInfo();
            clearNodes();
            return;
        } else if (!Data.addHotel(new Hotel(username, password, rooms))) {
            AlertsLoader.invalidUsername();
            clearNodes();
            return;
        }
        Data.thereAreChanges = true;
        login();
    }

    @FXML
    public void login() {
        LoginController.load();
    }

    private boolean validAccountInfo(String username, String password) {
        if (username.isEmpty() || password.isEmpty() ||
                username.length() < 3 || password.length() < 8 ||
                username.length() > 32 || password.length() > 32)
            return false;

        boolean usernameContainsOnlySpaces = true, passwordContainsOnlySpaces = true;

        for (int i = 0; i < username.length(); i++)
            if (username.charAt(i) != ' ') {
                usernameContainsOnlySpaces = false;
                break;
            }

        for (int i = 0; i < password.length(); i++)
            if (username.charAt(i) != ' ') {
                passwordContainsOnlySpaces = false;
                break;
            }

        return !(usernameContainsOnlySpaces || passwordContainsOnlySpaces);
    }

    private void clearNodes() {
        this.username.setText("");
        this.password.setText("");
        this.rooms.setText("");
    }
}
