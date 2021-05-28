package com.application.controllers;

import com.application.Run;
import com.application.model.Data;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReserveController implements Initializable {

    public static Stage stage;

    @FXML
    Label roomID, price, status;

    @FXML
    TextField owner;

    public static void load() {
        try {
            Parent root = FXMLLoader.load(Run.class.getResource("fxml/ReserveWindow.fxml"));
            stage = new Stage();
            stage.setTitle("Reserve");
            stage.setScene(new Scene(root, 300, 220));
            stage.setResizable(false);
            stage.getIcons().add(new Image(Run.class.getResource("icon.png").toString()));
            stage.show();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.roomID.setText("Room ID: " + Data.room.getRoomID());
        this.price.setText("Price: " + Data.room.getPrice());
        this.status.setText("Status: " + Data.room.getStatus());
    }

    @FXML
    public void reserve() {
        if (!Data.hotel.reserve(Data.room, this.owner.getText())) {
            AlertsLoader.invalidOwner();
            return;
        }
        Data.refreshMainWindow();
        Data.thereAreChanges = true;
        stage.close();
    }
}
