package com.vimensa.ship.shipper.response;

import com.vimensa.ship.shipper.model.Destination;
import com.vimensa.ship.shipper.model.Order;

import java.util.List;

public class GetOrder extends BaseResponse{
    private int error;
    private String od_id;
    private String adv_paym;
    private int item_type;
    private String note;
    private String from;
    private double from_lat;
    private double from_log;
    private String custm_phone;
    private double distance;
    private double fee;
    private String wait_time;
    private List<Destination> toLi;

    public GetOrder() {
    }

    public GetOrder(Order o, List<Destination> li) {
        this.toLi = li;
        this.od_id = o.getOd_id();
        this.adv_paym = o.getAdv_paym();
        this.item_type = o.getItem_type();
        this.note = o.getNote();
        this.from = o.getFrom();
        this.from_lat = o.getFrom_lat();
        this.from_log = o.getFrom_log();
        this.custm_phone = o.getCustm_phone();
        this.distance = o.getDistance();
        this.fee = o.getFee();
        this.wait_time = o.getWait_time();
    }

    public List<Destination> getToLi() {
        return toLi;
    }

    public String getOd_id() {
        return od_id;
    }

    public void setOd_id(String od_id) {
        this.od_id = od_id;
    }

    public void setToLi(List<Destination> toLi) {
        this.toLi = toLi;
    }

    public int getError() {
        return error;
    }

    public String getAdv_paym() {
        return adv_paym;
    }

    public void setAdv_paym(String adv_paym) {
        this.adv_paym = adv_paym;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public double getFrom_lat() {
        return from_lat;
    }

    public void setFrom_lat(double from_lat) {
        this.from_lat = from_lat;
    }

    public double getFrom_log() {
        return from_log;
    }

    public void setFrom_log(double from_log) {
        this.from_log = from_log;
    }

    public String getCustm_phone() {
        return custm_phone;
    }

    public void setCustm_phone(String custm_phone) {
        this.custm_phone = custm_phone;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public String getWait_time() {
        return wait_time;
    }

    public void setWait_time(String wait_time) {
        this.wait_time = wait_time;
    }

    public void setError(int error) {
        this.error = error;
    }


}
