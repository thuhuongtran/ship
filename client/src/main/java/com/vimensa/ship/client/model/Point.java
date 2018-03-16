package com.vimensa.ship.client.model;

public class Point {
    private String id;
    private double lat;
    private double log;
    private boolean active=true;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Point() {
    }

    public Point(String id, double lat, double log) {
        this.id = id;
        this.lat = lat;
        this.log = log;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }


}
