package com.vimensa.system.dao;

import com.vimensa.system.service.KdTree;

public class Order {
    private String order_id;
    private double from_lat;
    private double from_log;
    private double to_lat;
    private double to_log;
    private long wait_time;

    public Order() {
    }

    public Order(long wait_time) {
        this.wait_time = wait_time;
    }

    public Order(String order_id, double from_lat, double from_log, double to_lat, double to_log, long wait_time) {
        this.order_id = order_id;
        this.from_lat = from_lat;
        this.from_log = from_log;
        this.to_lat = to_lat;
        this.to_log = to_log;
        this.wait_time = wait_time;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
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

    public long getWait_time() {
        return wait_time;
    }

    public void setWait_time(long wait_time) {
        this.wait_time = wait_time;
    }
    public KdTree.XYZPoint toOriginXYZPoint(){
        return new KdTree.XYZPoint(this.from_lat, this.from_log, this.order_id);
    }
}
