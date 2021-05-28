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

    public static Stage stage;

    @FXML
    TextField rooms;

    public static void load() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(LOCATION);
            stage.setScene(new Scene(root, 275, 75));
            stage.setTitle("Add");
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
    public void add() {
        int newRooms;
        try {
            newRooms = Integer.parseInt(this.rooms.getText());
            if (newRooms <= 0) {
                this.rooms.setText("");
                return;
            }
            if (newRooms > 50) {
                this.rooms.setText("");
                AlertsLoader.invalidNumberOfRooms();
                return;
            }
        } catch (NumberFormatException e) {
            return;
        }
        Data.hotel.addRooms(newRooms);
        Data.refreshHotelInfo();
        Data.thereAreChanges = true;
        stage.close();
    }
}
