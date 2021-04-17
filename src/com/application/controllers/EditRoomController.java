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

    private static Stage STAGE;

    @FXML
    TextField roomID, owner, price, status, reservedOn;

    public static void LOAD() {
        try {
            STAGE = new Stage();
            Parent root = FXMLLoader.load(Run.class.getResource("fxml/EditRoomWindow.fxml"));
            STAGE.setScene(new Scene(root, 300, 300));
            STAGE.setTitle(Data.ROOM.getOwner() + "'s room");
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
        if (!Data.ROOM.isReserved())
            this.owner.setDisable(true);
        this.roomID.setText(Data.ROOM.getRoomID() + "");
        this.owner.setText(Data.ROOM.getOwner());
        this.price.setText(Data.ROOM.getPrice() + "");
        this.status.setText(Data.ROOM.getStatus());
        this.reservedOn.setText(Data.ROOM.getReservationDate());
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
        Data.ROOM.setOwner(this.owner.getText());
        Data.ROOM.setPrice(price);
        Data.REFRESH_ROOM();
        STAGE.close();
    }
}
