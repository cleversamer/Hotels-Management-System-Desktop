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

    public static void LOAD() {
        try {
            Parent root = FXMLLoader.load(LOCATION);
            Run.STAGE.setScene(new Scene(root, 660, 282));
            Run.STAGE.setTitle("Main");
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
        } catch (IOException e) {
            System.out.println("IOException");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.name.setText("Name: " + Data.HOTEL.getName() + "'s Hotel");
        this.created.setText("Created on: " + Data.HOTEL.getDate());
        this.rooms.setText("Rooms: " + Data.HOTEL.getRooms());
        this.available.setText("Available: " + Data.HOTEL.getAvailableRoomsList().size());
        this.reserved.setText("Reserved: " + Data.HOTEL.getReservedRoomsList().size());

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
    public void addRooms() {
        AddRoomsController.LOAD();
    }

    @FXML
    public void viewAvailableRooms() {
        List<Room> availableRoomsList = Data.HOTEL.getAvailableRoomsList();
        this.roomsFound.getItems().clear();
        if (availableRoomsList.isEmpty()) return;
        this.roomsFound.getItems().addAll(availableRoomsList);
        this.roomsFound.getSelectionModel().selectFirst();
        clearFields();
    }

    @FXML
    public void viewReservedRooms() {
        List<Room> reservedRoomsList = Data.HOTEL.getReservedRoomsList();
        this.roomsFound.getItems().clear();
        if (reservedRoomsList.isEmpty()) return;
        this.roomsFound.getItems().addAll(reservedRoomsList);
        this.roomsFound.getSelectionModel().selectFirst();
        clearFields();
    }

    @FXML
    public void editPassword() {
        EditPasswordController.LOAD();
    }

    @FXML
    public void search() {
        int roomID;
        try {
            roomID = Integer.parseInt(this.fieldRoomID.getText());
        } catch (NumberFormatException e) {
            return;
        }
        List<Room> rooms = Data.HOTEL.findRoom(this.fieldRoomOwner.getText(), roomID);
        if (rooms == null || rooms.isEmpty()) return;
        this.roomsFound.getItems().clear();
        this.roomsFound.getItems().addAll(rooms);
        this.roomsFound.getSelectionModel().selectFirst();
        Data.ROOM = this.roomsFound.getSelectionModel().getSelectedItem();
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
        Data.ROOM = this.roomsFound.getSelectionModel().getSelectedItem();
        Data.REFRESH_ROOM();
        this.edit.setDisable(false);
        if (Data.ROOM.isReserved()) {
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
    public void reserveAll() {
        clearResults();
        Data.HOTEL.reserveAll();
        Data.REFRESH_HOTEL();
    }

    @FXML
    public void emptyAll() {
        clearResults();
        Data.HOTEL.emptyAll();
        Data.REFRESH_HOTEL();
    }

    @FXML
    public void removeAll() {
        clearResults();
        Data.HOTEL.removeAllRooms();
        Data.REFRESH_HOTEL();
    }

    @FXML
    public void checkout() {
        Data.HOTEL.checkout(Data.ROOM);
        Data.REFRESH_MAIN();
        this.reserve.setDisable(false);
        this.checkout.setDisable(true);
    }

    @FXML
    public void edit() {
        EditRoomController.LOAD();
    }

    @FXML
    public void logout() {
        Data.CLEAR();
        LoginController.LOAD();
    }

    private void clearFields() {
        this.fieldRoomOwner.setText("");
        this.fieldRoomID.setText("");
    }
}
