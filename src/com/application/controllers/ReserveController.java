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

    public static Stage STAGE;

    @FXML
    Label roomID, price, status;

    @FXML
    TextField owner;

    public static void LOAD() {
        try {
            Parent root = FXMLLoader.load(Run.class.getResource("fxml/ReserveWindow.fxml"));
            STAGE = new Stage();
            STAGE.setTitle("Reserve");
            STAGE.setScene(new Scene(root, 300, 220));
            STAGE.setResizable(false);
            STAGE.getIcons().add(new Image(Run.class.getResource("icon.jpg").toString()));
            STAGE.show();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.roomID.setText("Room ID: " + Data.ROOM.getRoomID());
        this.price.setText("Price: " + Data.ROOM.getPrice());
        this.status.setText("Status: " + Data.ROOM.getStatus());
    }

    @FXML
    public void reserve() {
        if (!Data.HOTEL.reserve(Data.ROOM, this.owner.getText())) {
            AlertsLoader.INVALID_OWNER();
            return;
        }
        Data.REFRESH_MAIN();
        STAGE.close();
    }
}
