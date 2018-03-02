package com.vimensa.system.model;

import com.vimensa.system.service.KdTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DriverManagement {
    /**
     * @param drivers
     * @return driver to XYZPoints
     * */
    public static List<KdTree.XYZPoint> toXYZPoints(List<Driver> drivers){
        List<KdTree.XYZPoint> points = new ArrayList<>();
        for(Driver driver:drivers){
            points.add(driver.toXYZPoint());
        }
        return points;
    }
    /**
     * @param points
     * @return XYZPoints to drivers
     * */
    public static List<Driver> toDrivers(Collection<KdTree.XYZPoint> points){
        List<Driver> drivers = new ArrayList<>();
        for(KdTree.XYZPoint p: points){
            drivers.add(p.toDriver());
        }
        return drivers;
    }
    /**
     * convert drivers-lat-log to string
     * @param drivers
     * @return str
     * */
    public static String toString(List<Driver>drivers){
        String str = "";
        for (int i = 0; i < drivers.size(); i++) {
            if (i == drivers.size() - 1) {
                str = str + drivers.get(i).getLatitude() + "%2C" + drivers.get(i).getLongitude();
            }
            else{
                str = str + drivers.get(i).getLatitude() + "%2C" + drivers.get(i).getLongitude()+ "%7C";
            }
        }
        return str;
    }
}
