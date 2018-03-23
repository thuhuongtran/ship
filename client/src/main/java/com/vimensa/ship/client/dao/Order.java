package com.vimensa.ship.client.dao;

public class Order {
    private String od_id;
    private String created_time;
    private String cli_id;
    private String shp_id;
    private String adv_paym;
    private int item_type;
    private String note;
    private String from;
    private String custm_phone;
    private double distance;
    private double fee;
    private String wait_time;

    public String getOd_id() {
        return od_id;
    }

    public void setOd_id(String od_id) {
        this.od_id = od_id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getCli_id() {
        return cli_id;
    }

    public void setCli_id(String cli_id) {
        this.cli_id = cli_id;
    }

    public String getShp_id() {
        return shp_id;
    }

    public void setShp_id(String shp_id) {
        this.shp_id = shp_id;
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
}
