package com.vimensa.system.model;

import com.vimensa.system.service.KdTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Point {
    private String id;
    private double lat;
    private double log;

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

    public KdTree.XYZPoint toXYZPoint(){
        return new KdTree.XYZPoint(this.lat, this.log, this.id);
    }
    public static List<KdTree.XYZPoint> toXYZPoints(List<Point> ps){
        List<KdTree.XYZPoint> points = new ArrayList<>();
        for(Point p : ps){
            points.add(p.toXYZPoint());
        }
        return points;
    }
    public static List<Point> toPoints(Collection<KdTree.XYZPoint> points){
        List<Point> ps = new ArrayList<>();
        for(KdTree.XYZPoint p: points){
            ps.add(p.toPoint());
        }
        return ps;
    }
}
