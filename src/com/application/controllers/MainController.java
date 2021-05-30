package com.application.controllers;

import com.application.Run;
import com.application.model.Data;
import com.application.model.Room;
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
    Button reserve, checkout, edit;

    public static void load() {
        try {
            Parent root = FXMLLoader.load(LOCATION);
            Run.stage.setScene(new Scene(root, 660, 282));
            Run.stage.setTitle("V1.12 Main");
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.name.setText("Name: " + Data.hotel.getName() + "'s Hotel");
        this.created.setText("Created on: " + Data.hotel.getDate());
        this.rooms.setText("Rooms: " + Data.hotel.getRooms());
        this.available.setText("Available: " + Data.hotel.getAvailableRoomsList().size());
        this.reserved.setText("Reserved: " + Data.hotel.getReservedRoomsList().size());

        Data.mainLabels[0] = this.name;
        Data.mainLabels[1] = this.created;
        Data.mainLabels[2] = this.rooms;
        Data.mainLabels[3] = this.available;
        Data.mainLabels[4] = this.reserved;

        Data.mainLabels[5] = this.roomID;
        Data.mainLabels[6] = this.price;
        Data.mainLabels[7] = this.status;
        Data.mainLabels[8] = this.reservedOn;
        Data.mainLabels[9] = this.owner;
    }

    @FXML
    public void addRooms() {
        AddRoomsController.load();
    }

    @FXML
    public void viewAvailableRooms() {
        List<Room> availableRoomsList = Data.hotel.getAvailableRoomsList();
        this.roomsFound.getItems().clear();
        if (availableRoomsList.isEmpty()) return;
        this.roomsFound.getItems().addAll(availableRoomsList);
        this.roomsFound.getSelectionModel().selectFirst();
        clearFields();
    }

    @FXML
    public void viewReservedRooms() {
        List<Room> reservedRoomsList = Data.hotel.getReservedRoomsList();
        this.roomsFound.getItems().clear();
        if (reservedRoomsList.isEmpty()) return;
        this.roomsFound.getItems().addAll(reservedRoomsList);
        this.roomsFound.getSelectionModel().selectFirst();
        clearFields();
    }

    @FXML
    public void editPassword() {
        EditPasswordController.load();
    }

    @FXML
    public void search() {
        int roomID;
        try {
            roomID = Integer.parseInt(this.fieldRoomID.getText().trim());
        } catch (NumberFormatException e) {
            roomID = -1;
        }
        List<Room> rooms = Data.hotel.findRoom(this.fieldRoomOwner.getText(), roomID);
        if (rooms == null || rooms.isEmpty()) return;
        this.roomsFound.getItems().clear();
        this.roomsFound.getItems().addAll(rooms);
        this.roomsFound.getSelectionModel().selectFirst();
        Data.room = this.roomsFound.getSelectionModel().getSelectedItem();
        clearFields();
    }

    @FXML
    public void clearResults() {
        this.roomsFound.getItems().clear();
        this.reserve.setDisable(true);
        this.checkout.setDisable(true);
        this.edit.setDisable(true);
        this.roomID.setText("Room ID:");
        this.price.setText("Price:");
        this.status.setText("Status:");
        this.reservedOn.setText("Reserved on:");
        this.owner.setText("Owner:");
    }

    @FXML
    public void handleRoomsFound() {
        if (this.roomsFound.getItems().isEmpty()) return;
        Data.room = this.roomsFound.getSelectionModel().getSelectedItem();
        Data.refreshRoomInfo();
        this.edit.setDisable(false);
        if (Data.room.isReserved()) {
            this.checkout.setDisable(false);
            this.reserve.setDisable(true);
            return;
        }
        this.checkout.setDisable(true);
        this.reserve.setDisable(false);
    }

    @FXML
    public void reserve() {
        ReserveController.load();
        this.reserve.setDisable(true);
        this.checkout.setDisable(false);
        Data.thereAreChanges = true;
    }

    @FXML
    public void reserveAll() {
        clearResults();
        Data.hotel.reserveAll();
        Data.refreshHotelInfo();
        Data.thereAreChanges = true;
    }

    @FXML
    public void emptyAll() {
        clearResults();
        Data.hotel.emptyAll();
        Data.refreshHotelInfo();
        Data.thereAreChanges = true;
    }

    @FXML
    public void removeAll() {
        clearResults();
        Data.hotel.removeAllRooms();
        Data.refreshHotelInfo();
        Data.thereAreChanges = true;
    }

    @FXML
    public void checkout() {
        Data.hotel.checkout(Data.room);
        Data.refreshMainWindow();
        this.reserve.setDisable(false);
        this.checkout.setDisable(true);
        Data.thereAreChanges = true;
    }

    @FXML
    public void edit() {
        EditRoomController.load();
    }

    @FXML
    public void logout() {
        Data.clearInfo();
        LoginController.load();
    }

    private void clearFields() {
        this.fieldRoomOwner.setText("");
        this.fieldRoomID.setText("");
    }
}
