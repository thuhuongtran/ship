package com.vimensa.get_shipper.response;

public class ShipperResponse {
    private int error;
    private String shipperPhone;
    private double shipperLat;
    private double shipperLog;

    public double getShipperLat() {
        return shipperLat;
    }

    public void setShipperLat(double shipperLat) {
        this.shipperLat = shipperLat;
    }

    public double getShipperLog() {
        return shipperLog;
    }

    public void setShipperLog(double shipperLog) {
        this.shipperLog = shipperLog;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getShipperPhone() {
        return shipperPhone;
    }

    public void setShipperPhone(String shipperPhone) {
        this.shipperPhone = shipperPhone;
    }
}
