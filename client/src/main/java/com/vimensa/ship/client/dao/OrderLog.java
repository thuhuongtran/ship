package com.vimensa.ship.client.dao;

public class OrderLog {
    private String orderID;
    private String timestamp;
    private int status;
    private String client_phone;
    private String shipper_phone;
    private int adv_paym;
    private int mass;
    private String note;
    private String from;
    private String to;
    private double distance;
    private double fee;

    public OrderLog() {
    }

    public OrderLog(String orderID, String timestamp, int status, String client_phone, String shipper_phone,
                    int adv_paym, int mass, String note, String from, String to, float distance, float fee) {
        this.orderID = orderID;
        this.timestamp = timestamp;
        this.status = status;
        this.client_phone = client_phone;
        this.shipper_phone = shipper_phone;
        this.adv_paym = adv_paym;
        this.mass = mass;
        this.note = note;
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.fee = fee;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public void setShipper_phone(String shipper_phone) {
        this.shipper_phone = shipper_phone;
    }

    public void setAdv_paym(int adv_paym) {
        this.adv_paym = adv_paym;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }


    public String getOrderID() {
        return orderID;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public String getShipper_phone() {
        return shipper_phone;
    }

    public int getAdv_paym() {
        return adv_paym;
    }

    public int getMass() {
        return mass;
    }

    public String getNote() {
        return note;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
}
