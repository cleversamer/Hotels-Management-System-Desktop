package com.application.controllers;

import com.application.model.Data;
import com.application.model.Room;
import com.application.Run;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public static final URL LOCATION = Run.class.getResource("fxml/MainWindow.fxml");

    @FXML
    Label name, created, rooms, available, reserved, roomID, price, status, reservedOn, owner;

    @FXML
    TextField fieldRoomOwner, fieldRoomID;

    @FXML
    ComboBox<Room> roomsFound;

    @FXML
    Button reserve, checkout;

    public static void LOAD() {
        try {
            Parent root = FXMLLoader.load(LOCATION);
            Run.currentStage.setScene(new Scene(root, 660, 282));
            Run.currentStage.setTitle("Main");
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.name.setText(Data.CURRENT_HOTEL.getName());
        this.created.setText(Data.CURRENT_HOTEL.getDate());
        this.rooms.setText(Data.CURRENT_HOTEL.getRooms());
        this.available.setText(Data.CURRENT_HOTEL.getAvailableRoomsList());
        this.reserved.setText(Data.CURRENT_HOTEL.getReservedRoomsList());

        Data.MAIN_LABELS[0] = this.name;
        Data.MAIN_LABELS[1] = this.created;
        Data.MAIN_LABELS[2] = this.rooms;
        Data.MAIN_LABELS[3] = this.available;
        Data.MAIN_LABELS[4] = this.reserved;

        Data.MAIN_LABELS[5] = this.roomID;
        Data.MAIN_LABELS[6] = this.price;
        Data.MAIN_LABELS[7] = this.status;
        Data.MAIN_LABELS[8] = this.reservedOn;
        Data.MAIN_LABELS[9] = this.owner;
    }

    @FXML
    public void search() {
        List<Room> rooms = Data.CURRENT_HOTEL.findRoom(this.fieldRoomOwner.getText(), this.fieldRoomID.getText());
        if (rooms == null || rooms.isEmpty()) return;

        for (Room temp : this.roomsFound.getItems())
            rooms.remove(temp);

        this.roomsFound.getItems().addAll(rooms);
        this.roomsFound.getSelectionModel().selectFirst();
        Data.CURRENT_ROOM = this.roomsFound.getSelectionModel().getSelectedItem();
        clearNodes();
    }

    @FXML
    public void clearRoomsFound() {
        this.roomsFound.getItems().clear();
        this.reserve.setDisable(true);
        this.checkout.setDisable(true);
        this.roomID.setText("Room ID:");
        this.price.setText("Price:");
        this.status.setText("Status:");
        this.reservedOn.setText("Reserved on:");
        this.owner.setText("Owner:");
    }

    @FXML
    public void handleRoomsFound() {
        if (this.roomsFound.getItems().isEmpty())
            return;
        Data.CURRENT_ROOM = this.roomsFound.getSelectionModel().getSelectedItem();
        this.roomID.setText(Data.CURRENT_ROOM.getRoomID());
        this.price.setText(Data.CURRENT_ROOM.getPrice());
        this.status.setText(Data.CURRENT_ROOM.getStatus());
        this.reservedOn.setText(Data.CURRENT_ROOM.getReservationDate());
        this.owner.setText(Data.CURRENT_ROOM.getOwner());
        if (Data.CURRENT_ROOM.isReserved) {
            this.checkout.setDisable(false);
            this.reserve.setDisable(true);
            return;
        }
        this.checkout.setDisable(true);
        this.reserve.setDisable(false);
    }

    @FXML
    public void reserve() {
        ReserveController.LOAD();
        this.reserve.setDisable(true);
        this.checkout.setDisable(false);
    }

    @FXML
    public void checkout() {
        Data.CURRENT_HOTEL.checkout(Data.CURRENT_ROOM);
        Data.REFRESH_MAIN();
        this.reserve.setDisable(false);
        this.checkout.setDisable(true);
    }

    @FXML
    public void logout() {
        Data.CLEAR();
        LoginController.LOAD();
    }

    private void clearNodes() {
        this.fieldRoomOwner.setText("");
        this.fieldRoomID.setText("");
    }
}
