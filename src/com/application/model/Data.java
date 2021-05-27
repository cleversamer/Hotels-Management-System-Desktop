package com.application.model;

import javafx.scene.control.Label;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<Hotel> hotels = new ArrayList<>();

    public static Hotel hotel;

    public static Room room;

    public static Label[] mainLabels = new Label[10];

    public static boolean printingData = true, thereAreChanges = false;

    public static boolean addHotel(Hotel hotel) {
        String name = hotel.getName().trim();
        for (Hotel temp : hotels)
            if (name.equalsIgnoreCase(temp.getName().trim()))
                return false;
        return hotels.add(hotel);
    }

    public static Hotel getHotel(String username, String password) {
        username = username.trim();
        password = password.trim();
        for (Hotel temp : hotels)
            if (temp.checkLoginInfo(username, password))
                return temp;
        return null;
    }

    public static void refreshMainWindow() {
        refreshHotelInfo();
        refreshRoomInfo();
    }

    public static void refreshHotelInfo() {
        Data.mainLabels[0].setText("Name: " + Data.hotel.getName() + "'s Hotel");
        Data.mainLabels[1].setText("Created on: " + Data.hotel.getDate());
        Data.mainLabels[2].setText("Rooms: " + Data.hotel.getRooms());
        Data.mainLabels[3].setText("Available: " + Data.hotel.getAvailableRoomsList().size());
        Data.mainLabels[4].setText("Reserved: " + Data.hotel.getReservedRoomsList().size());
    }

    public static void refreshRoomInfo() {
        if (room == null)
            return;
        Data.mainLabels[5].setText("Room ID: " + room.getRoomID());
        Data.mainLabels[6].setText("Price: " + room.getPrice());
        Data.mainLabels[7].setText("Status: " + room.getStatus());
        Data.mainLabels[8].setText("Reserved on: " + room.getReservationDate());
        Data.mainLabels[9].setText("Owner: " + room.getOwner());
    }

    public static void clearInfo() {
        hotel = null;
        room = null;
        mainLabels = new Label[10];
    }

    public static void importData() {
        printingData = false;
        try {
            ObjectInputStream data = new ObjectInputStream(new FileInputStream("hotels.dat"));
            Hotel hotel;
            while ((hotel = (Hotel) data.readObject()) != null)
                hotels.add(hotel);
        } catch (IOException | ClassNotFoundException ignored) {
        }
        printingData = true;
    }

    public static void exportData() {
        printingData = false;
        try {
            ObjectOutputStream data = new ObjectOutputStream(new FileOutputStream("hotels.dat"));
            for (Hotel hotel : hotels)
                data.writeObject(hotel);
        } catch (IOException ignored) {
        }
        printingData = true;
    }
}
