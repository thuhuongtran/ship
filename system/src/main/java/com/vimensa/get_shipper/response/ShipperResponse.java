package com.vimensa.get_shipper.response;

public class ShipperResponse {
    private int error;
    private String shipperPhone;

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
