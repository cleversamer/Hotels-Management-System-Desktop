package com.application.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Hotel {

    private final String name;
    private final Date dateCreated;
    private final int rooms;
    private final List<Room> roomsList, availableRoomsList, reservedRoomsList;
    private final String password;

    public Hotel(String name, String password, int rooms) {
        this.name = name;
        this.dateCreated = new Date();
        this.rooms = rooms;
        this.password = password;
        this.roomsList = new LinkedList<>();
        this.availableRoomsList = new LinkedList<>();
        this.reservedRoomsList = new LinkedList<>();
        initialize();
    }

    private void initialize() {
        for (int i = 1; i <= rooms; i++)
            roomsList.add(new Room("Empty", i + "", 0));
        availableRoomsList.addAll(roomsList);
    }

    public void reserve(Room room, String owner) {
        this.availableRoomsList.remove(room);
        room.reserve(owner);
        this.reservedRoomsList.add(room);
    }

    public void checkout(Room room) {
        this.reservedRoomsList.remove(room);
        room.checkout();
        this.availableRoomsList.add(room);
    }

    public List<Room> findRoom(String roomOwner, String roomID) {
        if (roomOwner.isEmpty() && roomID.isEmpty())
            return null;

        List<Room> rooms = new LinkedList<>();

        if (!roomOwner.isEmpty())
            rooms.addAll(findRoomByOwner(roomOwner));

        if (!roomID.isEmpty())
            rooms.add(findRoomByID(roomID));

        return rooms;
    }

    private Room findRoomByID(String roomID) {
        for (Room room : this.roomsList)
            if (room.checkID(roomID))
                return room;
        return null;
    }

    private List<Room> findRoomByOwner(String roomOwner) {
        List<Room> rooms = new LinkedList<>();
        for (Room room : this.roomsList)
            if (room.checkOwner(roomOwner))
                rooms.add(room);
        return rooms;
    }

    public boolean checkLoginInfo(String username, String password) {
        return username.equalsIgnoreCase(this.name) && password.equals(this.password);
    }

    public String getName() {
        return "Name: " + this.name + "'s Hotel";
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(this.dateCreated);
        return "Created on: " + date;
    }

    public String getRooms() {
        return "Rooms: " + this.rooms;
    }

    public String getAvailableRoomsList() {
        return "Available: " + this.availableRoomsList.size();
    }

    public String getReservedRoomsList() {
        return "Reserved: " + this.reservedRoomsList.size();
    }
}
