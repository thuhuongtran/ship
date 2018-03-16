package com.vimensa.system.model;

import com.vimensa.system.service.KdTree;

public class Order
{
    private String od_id;
    private double from_lat;
    private double from_log;
    private String wait_time;

    public Order(String od_id, double from_lat, double from_log) {
        this.od_id = od_id;
        this.from_lat = from_lat;
        this.from_log = from_log;
    }

    public Order() {
    }

    public String getWait_time() {
        return wait_time;
    }

    public void setWait_time(String wait_time) {
        this.wait_time = wait_time;
    }

    public String getOd_id() {
        return od_id;
    }

    public void setOd_id(String od_id) {
        this.od_id = od_id;
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
    public KdTree.XYZPoint toOriginXYZPoint(){
        return new KdTree.XYZPoint(this.from_lat, this.from_log, this.od_id);
    }

}
