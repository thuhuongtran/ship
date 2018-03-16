package com.vimensa.ship.client.model;

public class Edge {
    private String from_id;
    private String to_id;
    private double dist;

    public Edge() {
    }

    public Edge(String from_id, String to_id, double dist) {
        this.from_id = from_id;
        this.to_id = to_id;
        this.dist = dist;
    }

    public String getFrom_id() {
        return from_id;
    }

    public void setFrom_id(String from_id) {
        this.from_id = from_id;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }

    public double getDist() {
        return dist;
    }

    public void setDist(double dist) {
        this.dist = dist;
    }
}
