package com.vimensa.system.dao;

import com.vimensa.system.service.KdTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Shipper {
    private String shp_id;
    private double lat;
    private double log;
    private int status;

    public String getShp_id() {
        return shp_id;
    }

    public void setShp_id(String shp_id) {
        this.shp_id = shp_id;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public KdTree.XYZPoint toXYZPoint(){
        return new KdTree.XYZPoint(this.lat, this.log, this.shp_id);
    }

    public static List<KdTree.XYZPoint> shpToXYZPoints(List<Shipper> shps){
        List<KdTree.XYZPoint> points = new ArrayList<>();
        for(Shipper s:shps){
            points.add(s.toXYZPoint());
        }
        return points;
    }
    public static List<Shipper> xyzToShippers(Collection<KdTree.XYZPoint> points){
        List<Shipper> shps = new ArrayList<>();
        for(KdTree.XYZPoint p: points){
            shps.add(p.toDriver());
        }
        return shps;
    }

}
