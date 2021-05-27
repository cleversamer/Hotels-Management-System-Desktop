package com.application.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Room implements Serializable {

    private final int roomID;
    private boolean isReserved;
    private double price;
    private String owner;
    private Date reservationDate;

    public Room(int roomID, double price) {
        this.roomID = roomID;
        this.price = price;
        this.owner = null;
        this.isReserved = false;
        this.reservationDate = null;
    }

    public Room(String owner, int roomID, double price) {
        this(roomID, price);
        this.owner = owner;
    }

    public void reserve(String owner) {
        this.owner = "x";
        this.setOwner(owner);
        if (this.owner.equals("x")) return;
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

    public boolean checkID(int roomID) {
        return this.roomID == roomID;
    }

    public int getRoomID() {
        return this.roomID;
    }

    public boolean isReserved() {
        return this.isReserved;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        owner = owner.trim();
        if (owner.isEmpty() || owner.length() < 3 || owner.length() > 26)
            return;
        this.owner = owner;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0)
            return;
        this.price = price;
    }

    public String getStatus() {
        return this.isReserved ? "Reserved" : "Empty";
    }

    public String getReservationDate() {
        if (this.reservationDate == null)
            return "00/00/0000";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy - HH:mm:ss");
        return dateFormat.format(this.reservationDate);
    }

    @Override
    public String toString() {
        if (Data.printingData)
            return this.roomID + " - " + this.owner;

        return "Room{" +
                "roomID=" + roomID +
                ", isReserved=" + isReserved +
                ", price=" + price +
                ", owner='" + owner + '\'' +
                ", reservationDate=" + reservationDate +
                '}';
    }
}
