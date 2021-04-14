package com.application.controllers;

import com.application.Run;
import com.application.model.Data;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AddRoomsController {

    protected static final URL LOCATION = Run.class.getResource("fxml/AddRoomsWindow.fxml");

    public static Stage STAGE;

    @FXML
    TextField rooms;

    public static void LOAD() {
        try {
            STAGE = new Stage();
            Parent root = FXMLLoader.load(LOCATION);
            STAGE.setScene(new Scene(root, 200, 75));
            STAGE.setTitle("Add");
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
    public void add() {
        int newRooms;
        try {
            newRooms = Integer.parseInt(this.rooms.getText());
            if (newRooms <= 0) {
                this.rooms.setText("");
                return;
            }
        } catch (NumberFormatException e) {
            return;
        }
        Data.HOTEL.addRooms(newRooms);
        Data.REFRESH_HOTEL();
        STAGE.close();
    }
}
