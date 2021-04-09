package com.application.controllers;

import com.application.Run;
import com.application.model.Data;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReserveController implements Initializable {

    public static final URL LOCATION = Run.class.getResource("fxml/ReserveWindow.fxml");

    public static Stage STAGE;

    @FXML
    Label roomID, price, status;

    @FXML
    TextField owner;

    public static void LOAD() {
        try {
            Parent root = FXMLLoader.load(LOCATION);
            STAGE = new Stage();
            STAGE.setTitle("Reserve");
            STAGE.setScene(new Scene(root, 300, 220));
            STAGE.setResizable(false);
            STAGE.show();
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @FXML
    public void reserve() {
        if (!validOwner(this.owner.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid inputs");
            alert.setHeaderText("Owner name is not valid");
            alert.setContentText("Please select a valid name");
            alert.show();
            return;
        }
        Data.CURRENT_HOTEL.reserve(Data.CURRENT_ROOM, this.owner.getText());
        Data.REFRESH_MAIN();
        STAGE.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.roomID.setText(Data.CURRENT_ROOM.getRoomID());
        this.price.setText(Data.CURRENT_ROOM.getPrice());
        this.status.setText(Data.CURRENT_ROOM.getStatus());
    }

    private boolean validOwner(String owner) {
        if (owner.isEmpty() || owner.length() < 3)
            return false;
        boolean containsOnlySpaces = true;
        for (int i = 0; containsOnlySpaces && i < owner.length(); i++)
            containsOnlySpaces = owner.charAt(i) == ' ';
        return !containsOnlySpaces;
    }
}
