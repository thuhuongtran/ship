package com.vimensa.ship.client.response;

import com.vimensa.ship.client.dao.Destination;
import com.vimensa.ship.client.dao.Order;
import com.vimensa.ship.client.dao.OrderStatus;

import java.util.List;

public class OrderHistory extends BaseResponse{
    private Order od_detail;
    private List<OrderStatus> od_status;
    private List<Destination> toLi;
    private int error;


    public OrderHistory() {
    }

    public OrderHistory(Order od_detail, List<OrderStatus> od_status, List<Destination> toLi) {
        this.od_detail = od_detail;
        this.od_status = od_status;
        this.toLi = toLi;
    }
    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public Order getOd_detail() {
        return od_detail;
    }

    public void setOd_detail(Order od_detail) {
        this.od_detail = od_detail;
    }

    public List<OrderStatus> getOd_status() {
        return od_status;
    }

    public void setOd_status(List<OrderStatus> od_status) {
        this.od_status = od_status;
    }

    public List<Destination> getToLi() {
        return toLi;
    }

    public void setToLi(List<Destination> toLi) {
        this.toLi = toLi;
    }
}
