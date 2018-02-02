package com.vimensa.get_shipper.model;

import com.vimensa.get_shipper.service.KdTree;

public class Order {
    private String id;
    private double fromLat;
    private double fromLog;
    private double toLat;
    private double toLog;

    public Order() {
    }

    public Order(String id, double fromLat, double fromLog, double toLat, double toLog) {
        this.id = id;
        this.fromLat = fromLat;
        this.fromLog = fromLog;
        this.toLat = toLat;
        this.toLog = toLog;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getFromLat() {
        return fromLat;
    }

    public void setFromLat(double fromLat) {
        this.fromLat = fromLat;
    }

    public double getFromLog() {
        return fromLog;
    }

    public void setFromLog(double fromLog) {
        this.fromLog = fromLog;
    }

    public double getToLat() {
        return toLat;
    }

    public void setToLat(double toLat) {
        this.toLat = toLat;
    }

    public double getToLog() {
        return toLog;
    }

    public void setToLog(double toLog) {
        this.toLog = toLog;
    }
    public KdTree.XYZPoint toOriginXYZPoint(){
        return new KdTree.XYZPoint(this.fromLat, this.fromLog, this.id);
    }
}
