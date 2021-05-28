package com.application.controllers;

import javafx.scene.control.Alert;

public class AlertsLoader {
    
    public static void invalidNumberOfRooms() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Not allowed");
        alert.setHeaderText("Operation is not allowed, you can add a maximum of 50 rooms each time.");
        alert.setContentText("Please try again.");
        alert.show();
    }

    public static void invalidOwner() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Owner name is not valid");
        alert.setContentText("Please select a valid name" +
                "\nHINT: owner's name must be in range(5-26 characters)");
        alert.show();
    }

    public static void invalidPassword() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Password is not valid");
        alert.setContentText("HINT: valid password must be in range(8-32 chars)");
        alert.show();
    }

    public static void invalidSignupInfo() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid username or password");
        alert.setContentText("username must be in range(5-32 characters)" +
                "\npassword must be in range(8-32 characters)");
        alert.show();
    }

    public static void invalidUsername() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Username is already taken");
        alert.setContentText("Please select another valid username");
        alert.show();
    }

    public static void invalidLoginInfo() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Invalid username or password");
        alert.setContentText("HINT: you can login as \nusername: \"admin\"\npassword: \"admin\"");
        alert.show();
    }
}
