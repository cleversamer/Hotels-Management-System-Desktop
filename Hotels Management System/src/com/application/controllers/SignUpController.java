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
            Run.currentStage.setScene(new Scene(root, 400, 250));
            Run.currentStage.setTitle("Sign Up");
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Length of username must be greater than 4 characters," +
                    "\nlength of password must be greater than 7 characters.");
            alert.setContentText("Please enter a valid username or password");
            alert.show();
            clearNodes();
            return;
        }
        if (!Data.ADD_HOTEL(new Hotel(username, password, rooms))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Username is already taken");
            alert.setContentText("Please select a valid username");
            alert.show();
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
