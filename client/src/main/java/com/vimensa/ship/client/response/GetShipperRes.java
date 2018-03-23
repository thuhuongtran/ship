package com.vimensa.ship.client.response;

public class GetShipperRes extends BaseResponse{
    private String phone;
    private String name;
    private String shp_id;
    private int star;
    private String avatar;
    private int error;

    public GetShipperRes() {
    }

    public GetShipperRes(String phone, String name, String shp_id, int star, String avatar) {
        this.phone = phone;
        this.name = name;
        this.shp_id = shp_id;
        this.star = star;
        this.avatar = avatar;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShp_id() {
        return shp_id;
    }

    public void setShp_id(String shp_id) {
        this.shp_id = shp_id;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
