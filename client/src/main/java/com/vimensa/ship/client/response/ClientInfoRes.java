package com.vimensa.ship.client.response;

public class ClientInfoRes {
    private String phone;
    private String code;
    private String name;
    private String mail;
    private String place;
    private String cli_id;
    private String avatar;
    private int error;

    public ClientInfoRes(String phone, String code, String name, String mail, String place, String cli_id, String avatar) {
        this.phone = phone;
        this.code = code;
        this.name = name;
        this.mail = mail;
        this.place = place;
        this.cli_id = cli_id;
        this.avatar = avatar;
    }

    public ClientInfoRes() {
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCli_id() {
        return cli_id;
    }

    public void setCli_id(String cli_id) {
        this.cli_id = cli_id;
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
