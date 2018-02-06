package com.vimensa.ship.client.response;

public class NewOrderResponse {
    private int error;
    private String shipper_phone;
    private String shipper_name;
    private double star;
    private String shipper_lat;
    private String shipper_log;
    private double fee;

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getShipper_lat() {
        return shipper_lat;
    }

    public void setShipper_lat(String shipper_lat) {
        this.shipper_lat = shipper_lat;
    }

    public String getShipper_log() {
        return shipper_log;
    }

    public void setShipper_log(String shipper_log) {
        this.shipper_log = shipper_log;
    }

    public String getShipper_phone() {
        return shipper_phone;
    }

    public void setShipper_phone(String shipper_phone) {
        this.shipper_phone = shipper_phone;
    }

    public String getShipper_name() {
        return shipper_name;
    }

    public void setShipper_name(String shipper_name) {
        this.shipper_name = shipper_name;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

}
