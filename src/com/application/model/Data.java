package com.application.model;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<Hotel> HOTELS = new ArrayList<>();

    public static Hotel CURRENT_HOTEL;

    public static Room CURRENT_ROOM;

    public static Label[] MAIN_LABELS = new Label[10];

    public static boolean ADD_HOTEL(Hotel hotel) {
        String name = hotel.getName().trim();
        for (Hotel temp : HOTELS)
            if (name.equalsIgnoreCase(temp.getName().trim()))
                return false;
        return HOTELS.add(hotel);
    }

    public static Hotel GET_HOTEL(String username, String password) {
        for (Hotel temp : HOTELS)
            if (temp.checkLoginInfo(username, password))
                return temp;
        return null;
    }

    public static void REFRESH_MAIN() {
        Data.MAIN_LABELS[0].setText(CURRENT_HOTEL.getName());
        Data.MAIN_LABELS[1].setText(CURRENT_HOTEL.getDate());
        Data.MAIN_LABELS[2].setText(CURRENT_HOTEL.getRooms());
        Data.MAIN_LABELS[3].setText(CURRENT_HOTEL.getAvailableRoomsList());
        Data.MAIN_LABELS[4].setText(CURRENT_HOTEL.getReservedRoomsList());

        Data.MAIN_LABELS[5].setText(CURRENT_ROOM.getRoomID());
        Data.MAIN_LABELS[6].setText(CURRENT_ROOM.getPrice());
        Data.MAIN_LABELS[7].setText(CURRENT_ROOM.getStatus());
        Data.MAIN_LABELS[8].setText(CURRENT_ROOM.getReservationDate());
        Data.MAIN_LABELS[9].setText(CURRENT_ROOM.getOwner());
    }

    public static void CLEAR() {
        CURRENT_HOTEL = null;
        CURRENT_ROOM = null;
        MAIN_LABELS = new Label[10];
    }
}
