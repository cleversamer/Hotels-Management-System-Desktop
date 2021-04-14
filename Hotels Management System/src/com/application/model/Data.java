package com.application.model;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<Hotel> HOTELS = new ArrayList<>();

    public static Hotel HOTEL;

    public static Room ROOM;

    public static Label[] MAIN_LABELS = new Label[10];

    public static boolean ADD_HOTEL(Hotel hotel) {
        String name = hotel.getName().trim();
        for (Hotel temp : HOTELS)
            if (name.equalsIgnoreCase(temp.getName().trim()))
                return false;
        return HOTELS.add(hotel);
    }

    public static Hotel GET_HOTEL(String username, String password) {
        username = username.trim();
        password = password.trim();
        for (Hotel temp : HOTELS)
            if (temp.checkLoginInfo(username, password))
                return temp;
        return null;
    }

    public static void REFRESH_MAIN() {
        REFRESH_HOTEL();
        REFRESH_ROOM();
    }

    public static void REFRESH_HOTEL() {
        Data.MAIN_LABELS[0].setText("Name: " + Data.HOTEL.getName() + "'s Hotel");
        Data.MAIN_LABELS[1].setText("Created on: " + Data.HOTEL.getDate());
        Data.MAIN_LABELS[2].setText("Rooms: " + Data.HOTEL.getRooms());
        Data.MAIN_LABELS[3].setText("Available: " + Data.HOTEL.getAvailableRoomsList().size());
        Data.MAIN_LABELS[4].setText("Reserved: " + Data.HOTEL.getReservedRoomsList().size());
    }

    public static void REFRESH_ROOM() {
        if (ROOM == null)
            return;
        Data.MAIN_LABELS[5].setText("Room ID: " + ROOM.getRoomID());
        Data.MAIN_LABELS[6].setText("Price: " + ROOM.getPrice());
        Data.MAIN_LABELS[7].setText("Status: " + ROOM.getStatus());
        Data.MAIN_LABELS[8].setText("Reserved on: " + ROOM.getReservationDate());
        Data.MAIN_LABELS[9].setText("Owner: " + ROOM.getOwner());
    }

    public static void CLEAR() {
        HOTEL = null;
        ROOM = null;
        MAIN_LABELS = new Label[10];
    }
}
