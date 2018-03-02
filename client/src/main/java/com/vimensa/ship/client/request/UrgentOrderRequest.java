package com.vimensa.ship.client.request;

public class UrgentOrderRequest {
    private double from_lat;
    private double from_log;
    private double to_lat;
    private double to_log;
    private String client_phone;
    private int item_type;
    private String adv_paym;
    private int mass;
    private String note;
    private String from;
    private String to;
    private double distance;

    public UrgentOrderRequest() {
    }

    public UrgentOrderRequest(double from_lat, double from_log, double to_lat, double to_log, String client_phone, int item_type, String adv_paym, int mass, String note, String from, String to, double distance) {
        this.from_lat = from_lat;
        this.from_log = from_log;
        this.to_lat = to_lat;
        this.to_log = to_log;
        this.client_phone = client_phone;
        this.item_type = item_type;
        this.adv_paym = adv_paym;
        this.mass = mass;
        this.note = note;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public double getFrom_lat() {
        return from_lat;
    }

    public void setFrom_lat(double from_lat) {
        this.from_lat = from_lat;
    }

    public double getFrom_log() {
        return from_log;
    }

    public void setFrom_log(double from_log) {
        this.from_log = from_log;
    }

    public double getTo_lat() {
        return to_lat;
    }

    public void setTo_lat(double to_lat) {
        this.to_lat = to_lat;
    }

    public double getTo_log() {
        return to_log;
    }

    public void setTo_log(double to_log) {
        this.to_log = to_log;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }

    public String getAdv_paym() {
        return adv_paym;
    }

    public void setAdv_paym(String adv_paym) {
        this.adv_paym = adv_paym;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}

