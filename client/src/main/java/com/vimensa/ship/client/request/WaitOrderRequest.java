package com.vimensa.ship.client.request;

import com.vimensa.ship.client.model.Destination;

import java.util.List;

public class WaitOrderRequest {
    private String cli_id;
    private String adv_paym;
    private int item_type;
    private String note;
    private String from;
    private double from_lat;
    private double from_log;
    private String custm_phone;
    private List<Destination> toLi;
    private double distance;
    private String wait_time;

    public WaitOrderRequest() {
    }

    public WaitOrderRequest(String cli_id, String adv_paym, int item_type, String note, String from, double from_lat, double from_log, String custm_phone, List<Destination> toLi, double distance, String wait_time) {
        this.cli_id = cli_id;
        this.adv_paym = adv_paym;
        this.item_type = item_type;
        this.note = note;
        this.from = from;
        this.from_lat = from_lat;
        this.from_log = from_log;
        this.custm_phone = custm_phone;
        this.toLi = toLi;
        this.distance = distance;
        this.wait_time = wait_time;
    }

    public String getCli_id() {
        return cli_id;
    }

    public void setCli_id(String cli_id) {
        this.cli_id = cli_id;
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

    public List<Destination> getToLi() {
        return toLi;
    }

    public void setToLi(List<Destination> toLi) {
        this.toLi = toLi;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getWait_time() {
        return wait_time;
    }

    public void setWait_time(String wait_time) {
        this.wait_time = wait_time;
    }
}
