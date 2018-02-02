package com.vimensa.get_shipper.model;

import com.vimensa.get_shipper.service.KdTree;

public class Driver {
    private String id;
    private double longitude;
    private double latitude;

    public Driver() {
    }

    public Driver(String id, double longitude, double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public KdTree.XYZPoint toXYZPoint(){
        return new KdTree.XYZPoint(this.latitude, this.longitude, this.id);
    }

}
