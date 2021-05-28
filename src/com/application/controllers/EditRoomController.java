package com.application.controllers;

import com.application.Run;
import com.application.model.Data;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditRoomController implements Initializable {

    private static Stage stage;

    @FXML
    TextField roomID, owner, price, status, reservedOn;

    public static void load() {
        try {
            stage = new Stage();
            Parent root = FXMLLoader.load(Run.class.getResource("fxml/EditRoomWindow.fxml"));
            stage.setScene(new Scene(root, 300, 300));
            String stageTitle = Data.room.isReserved() ? Data.room.getOwner() + "'s room" : "Empty room";
            stage.setTitle(stageTitle);
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
        if (!Data.room.isReserved())
            this.owner.setDisable(true);
        this.roomID.setText(Data.room.getRoomID() + "");
        this.owner.setText(Data.room.getOwner());
        this.price.setText(Data.room.getPrice() + "");
        this.status.setText(Data.room.getStatus());
        this.reservedOn.setText(Data.room.getReservationDate());
    }

    @FXML
    public void apply() {
        double price;
        try {
            price = Double.parseDouble(this.price.getText());
        } catch (NumberFormatException e) {
            this.owner.setText("");
            this.price.setText("");
            return;
        }
        Data.room.setOwner(this.owner.getText());
        Data.room.setPrice(price);
        Data.refreshRoomInfo();
        Data.thereAreChanges = true;
        stage.close();
    }
}
