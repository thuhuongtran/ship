package com.vimensa.ship.admin.response;

import java.util.List;

public class GetNewShipperRes extends BaseResponse
{
    private List<String> shipperLi;
    private List<String> clientLi;
    private int error;

    public GetNewShipperRes() {
    }

    public GetNewShipperRes(List<String> shipperLi, List<String> clientLi, int error) {
        this.shipperLi = shipperLi;
        this.clientLi = clientLi;
        this.error = error;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<String> getClientLi() {
        return clientLi;
    }

    public void setClientLi(List<String> clientLi) {
        this.clientLi = clientLi;
    }

    public List<String> getShipperLi() {
        return shipperLi;
    }

    public void setShipperLi(List<String> shipperLi) {
        this.shipperLi = shipperLi;
    }
}
