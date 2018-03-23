package com.vimensa.ship.client.dao;

public class Destination {
    private double to_lat;
    private double to_log;
    private String to;

    public Destination() {
    }

    public Destination(double to_lat, double to_log, String to) {
        this.to_lat = to_lat;
        this.to_log = to_log;
        this.to = to;
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

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
