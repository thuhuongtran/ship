package com.vimensa.system.service;

import com.google.gson.Gson;
import com.vimensa.system.RunAPI;
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
public class FindingBestShipper {
    private final static Logger logger = LoggerFactory.getLogger(RunAPI.class);
    @Autowired
    private static DataProcess dao;

    /**
     * call Kd-Tree.nearestNeighbor to return NUMNER_SHIPPER nearest drivers
     *
     * @param shps
     * @param sr
     * @return: shippers
     */
    public static List<Shipper> getNearShipperByKdTree(List<Shipper> shps, Order sr) {
        List<Shipper> shippers = new ArrayList<>();

        List<KdTree.XYZPoint> points = Shipper.shpToXYZPoints(shps);
        KdTree.XYZPoint search = sr.toOriginXYZPoint();
        KdTree<KdTree.XYZPoint> kdTree = new KdTree<KdTree.XYZPoint>(points);
        Collection<KdTree.XYZPoint> result = kdTree.nearestNeighbourSearch(Status.NUMBER_SHIPPER, search);
        shippers = Shipper.xyzToShippers(result);

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
     * @param shps
     * @param sr
     * @return distances
     */
    public static List<Edge> getGglDistancesBetweenShippersAndClient(List<Shipper> shps, Order sr) throws IOException {
        List<Edge> eli = new ArrayList<>();
        FindingBestShipper request = new FindingBestShipper();
        for (Shipper shp : shps) {
            String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?&origins=" + sr.getFrom_lat() + ","
                    + sr.getFrom_log() +
                    "&destinations=" + shp.getLat() + "%2C" + shp.getLog() + "&key=" + API_KEY;
            String response = request.run(url_request);

            //convert json to GoogleDistance obj
            GoogleDistance gglDis = new Gson().fromJson(response, GoogleDistance.class);

            Edge e = new Edge();
            e.setDistance(gglDis.rows.get(0).elements.get(0).distance.value);
            e.setShp_id(shp.getShp_id());
            e.setOd_id(sr.getOd_id());
            eli.add(e);
        }
        return eli;
    }


    /**
     * sort list of google distance
     * return the most suitable shipper who has smallest distance
     *
     * @param sr
     * @return driver - ID
     */
    public static Edge getClosestShipper(Order sr, List<Shipper> shps) throws IOException {
        List<Edge> eli = getGglDistancesBetweenShippersAndClient(getNearShipperByKdTree(shps,sr),sr);
        // sort distances by distance
        eli.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return (int) (o1.getDistance()-o2.getDistance());
            }
        });
        return eli.get(0);
    }

}
