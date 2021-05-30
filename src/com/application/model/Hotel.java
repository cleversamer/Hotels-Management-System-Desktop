package com.application.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Hotel implements Serializable {

    private final Date dateCreated;
    private final List<Room> roomsList;
    private final List<Room> availableRoomsList, reservedRoomsList;
    private final String name;
    private int rooms;
    private String password;

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
        for (int roomID = 1; roomID <= rooms; roomID++)
            roomsList.add(new Room("Empty", roomID, 0));
        availableRoomsList.addAll(roomsList);
    }

    public boolean reserve(Room room, String owner) {
        if (!valid(owner))
            return false;
        this.availableRoomsList.remove(room);
        room.reserve(owner);
        this.reservedRoomsList.add(room);
        this.sortReservedRoomsList();
        return true;
    }

    private boolean valid(String owner) {
        owner = owner.trim();
        return !owner.isEmpty() && owner.length() >= 5 && owner.length() <= 26;
    }

    public void reserveAll() {
        this.availableRoomsList.clear();
        this.reservedRoomsList.clear();
        for (Room room : this.roomsList) {
            if (!room.isReserved())
                room.reserve("Unknown");
            this.reservedRoomsList.add(room);
        }
    }

    public void checkout(Room room) {
        this.reservedRoomsList.remove(room);
        room.checkout();
        this.availableRoomsList.add(room);
        this.sortAvailableRoomsList();
    }

    public void emptyAll() {
        this.availableRoomsList.clear();
        this.reservedRoomsList.clear();
        for (Room room : this.roomsList) {
            if (room.isReserved())
                room.checkout();
            this.availableRoomsList.add(room);
        }
    }

    public void removeAllRooms() {
        this.roomsList.clear();
        this.availableRoomsList.clear();
        this.reservedRoomsList.clear();
    }

    public List<Room> findRoom(String roomOwner, int roomID) {
        roomOwner = roomOwner.trim();
        if (roomOwner.isEmpty() && roomID <= 0)
            return null;

        List<Room> rooms = new LinkedList<>();

        if (!roomOwner.isEmpty())
            rooms.addAll(findRoomByOwner(roomOwner));

        if (roomID > 0)
            rooms.add(findRoomByID(roomID));

        return rooms;
    }

    private Room findRoomByID(int roomID) {
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

    public void addRooms(int rooms) {
        int newRooms = this.getRooms() + rooms;
        for (int roomID = this.getRooms() + 1; roomID <= newRooms; roomID++) {
            Room temp = new Room("Empty", roomID, 0);
            this.roomsList.add(temp);
            this.availableRoomsList.add(temp);
        }
        this.rooms = newRooms;
    }

    public String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(this.dateCreated);
    }

    public List<Room> getAvailableRoomsList() {
        return availableRoomsList;
    }

    public List<Room> getReservedRoomsList() {
        return reservedRoomsList;
    }

    public String getName() {
        return name;
    }

    public int getRooms() {
        return this.roomsList.size();
    }

    public String getPassword() {
        return this.password;
    }

    public boolean setPassword(String password) {
        password = password.trim();
        if (password.isEmpty() || password.length() < 8 || password.length() > 32)
            return false;
        for (int i = 0; i < password.length(); i++)
            if (password.charAt(i) == ' ')
                return false;
        this.password = password;
        return true;
    }

    private void sortReservedRoomsList() {
        this.reservedRoomsList.sort(Comparator.comparingInt(Room::getRoomID));
    }

    private void sortAvailableRoomsList() {
        this.availableRoomsList.sort(Comparator.comparingInt(Room::getRoomID));
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "dateCreated=" + dateCreated +
                ", roomsList=" + roomsList +
                ", availableRoomsList=" + availableRoomsList +
                ", reservedRoomsList=" + reservedRoomsList +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                ", password='" + password + '\'' +
                '}';
    }
}
