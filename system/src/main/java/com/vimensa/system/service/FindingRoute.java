package com.vimensa.system.service;

import com.google.gson.Gson;
import com.vimensa.system.model.Distance;
import com.vimensa.system.model.GoogleDistance;
import com.vimensa.system.model.Point;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.*;

public class FindingRoute {

    public static String getRoute(List<Point> ps, Point p) throws IOException {
        String res = "";
        if(ps.size()==0){
            return res;
        }
        Map<String,Distance> distances = getGglDistances(ps, p);
        // sort distances by distance
        distances.sort(new Comparator<Distance>() {
            @Override
            public int compare(Distance o1, Distance o2) {
                return (int) (o1.getDistance()-o2.getDistance());
            }
        });
        res = res + distances.get(0).getId()+"_";
        int ind = Integer.parseInt(distances.get(0).getId()) - 1;
        Point newp = ps.get(ind);
        ps.remove(ind);
        return getRoute(ps,newp);
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
    public static Map<String,Distance> getGglDistances(List<Point> ps, Point start_point) throws IOException {
        Map<String,Distance> distances = new HashMap<>();
        OrderProcess request = new OrderProcess();
        for (Point p : ps) {
            String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?&origins=" + start_point.getLat() + ","
                    + start_point.getLog() +
                    "&destinations=" + p.getLat() + "%2C" + p.getLog() + "&key=" + API_KEY;
            String response = request.run(url_request);

            //convert json to GoogleDistance obj
            GoogleDistance gglDis = new Gson().fromJson(response, GoogleDistance.class);

            Distance dis = new Distance();
            dis.setDistance(gglDis.rows.get(0).elements.get(0).distance.value);
            dis.setId(p.getId());
            distances.put(dis.getId(), dis);
        }
        return distances;
    }
}
