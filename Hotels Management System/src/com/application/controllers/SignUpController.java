package com.application.controllers;

import com.application.Run;
import com.application.model.Data;
import com.application.model.Hotel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class SignUpController {

    public static final URL LOCATION = Run.class.getResource("fxml/SignUpWindow.fxml");

    @FXML
    TextField username;

    @FXML
    PasswordField password;

    @FXML
    Slider rooms;

    public static void LOAD() {
        try {
            Parent root = FXMLLoader.load(LOCATION);
            Run.STAGE.setScene(new Scene(root, 400, 250));
            Run.STAGE.setTitle("Sign Up");
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
        int rooms = (int) this.rooms.getValue();

        if (!validAccountInfo(username, password)) {
            AlertsLoader.INVALID_SIGNUP_INFO();
            clearNodes();
            return;
        } else if (!Data.ADD_HOTEL(new Hotel(username, password, rooms))) {
            AlertsLoader.INVALID_USERNAME();
            clearNodes();
            return;
        }
        login();
    }

    @FXML
    public void login() {
        LoginController.LOAD();
    }

    private boolean validAccountInfo(String username, String password) {
        if (username.isEmpty() || password.isEmpty() || username.length() < 5 || password.length() < 8)
            return false;
        boolean usernameContainsOnlySpaces = true;
        boolean passwordContainsOnlySpaces = true;
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
        this.rooms.setValue(this.rooms.getMin());
    }
}
