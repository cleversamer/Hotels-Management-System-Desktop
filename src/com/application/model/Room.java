package com.application.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Room {

    private final String roomID;
    private final double price;
    public boolean isReserved;
    private String owner;
    private Date reservationDate;

    public Room(String number, double price) {
        this.roomID = number;
        this.price = price;
        this.owner = null;
        this.isReserved = false;
        this.reservationDate = null;
    }

    public Room(String owner, String number, double price) {
        this(number, price);
        this.owner = owner;
    }

    public void reserve(String owner) {
        this.owner = owner;
        this.isReserved = true;
        this.reservationDate = new Date();
    }

    public void checkout() {
        this.owner = "Empty";
        this.isReserved = false;
        this.reservationDate = null;
    }

    public boolean checkOwner(String owner) {
        return this.owner.equalsIgnoreCase(owner);
    }

    public boolean checkID(String roomID) {
        return this.roomID.equals(roomID);
    }

    public String getRoomID() {
        return "Room ID: " + this.roomID;
    }

    public String getOwner() {
        return "Owner: " + this.owner;
    }

    public String getPrice() {
        return "Price: " + this.price;
    }

    public String getStatus() {
        return "Status: " + ((this.isReserved) ? "Reserved" : "Empty");
    }

    public String getReservationDate() {
        if (this.reservationDate == null)
            return "Reserved on:";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(this.reservationDate);
        return "Reserved on: " + date;
    }

    @Override
    public String toString() {
        return this.owner + " - " + this.roomID;
    }
}
