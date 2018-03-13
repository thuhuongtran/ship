package com.vimensa.system.service;

import com.google.gson.Gson;
import com.vimensa.system.RunAPI;
import com.vimensa.system.dao.Order;
import com.vimensa.system.dao.Shipper;
import com.vimensa.system.data.DataProcess;
import com.vimensa.system.model.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@Service
public class OrderProcess {
    private final static Logger logger = LoggerFactory.getLogger(RunAPI.class);
    @Autowired
    private static DataProcess dao;

    /**
     * call Kd-Tree.nearestNeighbor to return 5 nearest drivers
     *
     * @param drivers
     * @param origin
     * @return: shippers
     */
    public static List<Driver> getNearDrivers(List<Driver> drivers, Order origin) {
        List<Driver> shippers = new ArrayList<>();

        List<KdTree.XYZPoint> points = DriverManagement.toXYZPoints(drivers);
        KdTree.XYZPoint search = origin.toOriginXYZPoint();
        KdTree<KdTree.XYZPoint> kdTree = new KdTree<KdTree.XYZPoint>(points);
        Collection<KdTree.XYZPoint> result = kdTree.nearestNeighbourSearch(3, search);// HERE ----------
        shippers = DriverManagement.toDrivers(result);

        return shippers;
    }

    private static final String API_KEY = "AIzaSyBzFG6XODU-dIMWm9KS-m0gururGcvQTMI";
    static OkHttpClient client = new OkHttpClient();

    public static String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * call google distance matrix to return real distance
     *
     * @param drivers
     * @param origin
     * @return distances
     */
    public static List<Distance> getRealDistances(List<Driver> drivers, Order origin) throws IOException {
        List<Distance> distances = new ArrayList<>();
        OrderProcess request = new OrderProcess();
        for (Driver driver : drivers) {
            String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?&origins=" + origin.getFrom_lat() + ","
                    + origin.getFrom_log() +
                    "&destinations=" + driver.getLatitude() + "%2C" + driver.getLongitude() + "&key=" + API_KEY;
            String response = request.run(url_request);

            //convert json to GoogleDistance obj
            GoogleDistance gglDis = new Gson().fromJson(response, GoogleDistance.class);

            Distance dis = new Distance();
            dis.setDistance(gglDis.rows.get(0).elements.get(0).distance.value);
            dis.setId(driver.getId() + "_" + origin.getOrder_id());
            distances.add(dis);
        }
        return distances;
    }


    /**
     * sort list of real distance
     * return the most suitable driver who has smallest distance
     *
     * @param order
     * @return driver - ID
     */
    public static String getDriver(Order order, List<Driver> driverDB) throws IOException {
        String driver = "";
        List<Driver> drivers = OrderProcess.getNearDrivers(driverDB, order);
        List<Distance> distances = OrderProcess.getRealDistances(drivers, order);
        // sort distances by distance
        distances.sort(new Comparator<Distance>() {
            @Override
            public int compare(Distance o1, Distance o2) {
                return (int) (o1.getDistance()-o2.getDistance());
            }
        });
        int index = distances.get(0).getId().indexOf("_");
        driver = distances.get(0).getId().substring(0, index);
        return driver;
    }
    /**
     * shippers to drivers
     * */
    public static List<Driver> toDrivers(List<Shipper> shippers){
        List<Driver> drivers = new ArrayList<>();
        for(int i =0;i<shippers.size();i++){
            Driver driver = new Driver(shippers.get(i).getPhone(),shippers.get(i).getLog(),shippers.get(i).getLat());
            drivers.add(driver);
        }
        return drivers;
    }
}
