package com.vimensa.ship.shipper.response;

public class ShipperInfoRes extends BaseResponse{
    private String phone;
    private String code;
    private String name;
    private String mail;
    private String shp_id;
    private int star;
    private String avatar;
    private int error;

    public ShipperInfoRes(String phone, String code, String name, String mail, String shp_id, int star, String avatar) {
        this.phone = phone;
        this.code = code;
        this.name = name;
        this.mail = mail;
        this.shp_id = shp_id;
        this.star = star;
        this.avatar = avatar;
    }

    public ShipperInfoRes() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
