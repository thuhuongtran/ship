package com.vimensa.ship.client.service;

import com.google.gson.Gson;
import com.vimensa.ship.client.model.Edge;
import com.vimensa.ship.client.model.GoogleDistance;
import com.vimensa.ship.client.model.Point;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindingRoute {

    private static final String API_KEY = "AIzaSyBzFG6XODU-dIMWm9KS-m0gururGcvQTMI";
    static OkHttpClient client = new OkHttpClient();

    public static String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static List<Edge> getGglDistances(List<Point> ps, Point start_point) throws IOException, InterruptedException {
        List<Edge> li = new ArrayList<>();
        FindingRoute request = new FindingRoute();
        for (Point p : ps) {
            if (!p.isActive()) {
                continue;
            }
            else{
                String url_request = "https://maps.googleapis.com/maps/api/distancematrix/json?&origins=" + start_point.getLat() + ","
                        + start_point.getLog() +
                        "&destinations=" + p.getLat() + "%2C" + p.getLog() + "&key=" + API_KEY;
                String response = request.run(url_request);
                //convert json to obj
                GoogleDistance gglDis = new Gson().fromJson(response, GoogleDistance.class);
                Edge e = new Edge();
                e.setDist(gglDis.rows.get(0).elements.get(0).distance.value);
                e.setFrom_id(start_point.getId());
                e.setTo_id(p.getId());
                li.add(e);
            }

        }
        return li;
    }

    public static String getBestRoute(List<Point> ps, Point start_point) throws IOException, InterruptedException {
        String r = start_point.getId() + "_";
        if (ps.size() == 1) {
            return r + ps.get(0).getId();
        }
        List<Edge> eli = getGglDistances(ps, start_point);
        Edge e = getSmallestEdge(eli);
        r = r + e.getTo_id() + "_";
        Point sp = ps.get(Integer.parseInt(e.getTo_id()) - 1);
        sp.setActive(false);
        System.out.println(r);
        return r + getBestRoute(ps, sp);
    }

    public static Edge getSmallestEdge(List<Edge> li) {
        li.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return (int) (o1.getDist() - o2.getDist());
            }
        });
        return li.get(0);
    }

}
