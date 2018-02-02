package com.vimensa.ship.client.request;

public class NewOrder {
    private String client_phone;
    private int adv_paym;
    private int mass;
    private String note;
    private String from;
    private String to;
    private String from_lat;
    private String from_log;
    private String to_lat;
    private String to_log;
    private double distance;

    public String getClient_phone() {
        return client_phone;
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
}

