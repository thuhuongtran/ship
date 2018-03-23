package com.vimensa.ship.admin.response;

public class AdminInfo extends BaseResponse{
    private String adm_id;
    private String phone;
    private String code;
    private String name;
    private String mail;
    private String avatar;
    private int error;

    public AdminInfo(String adm_id, String phone, String code, String name, String mail, String avatar) {
        this.adm_id = adm_id;
        this.phone = phone;
        this.code = code;
        this.name = name;
        this.mail = mail;
        this.avatar = avatar;
    }

    public AdminInfo() {
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getAdm_id() {
        return adm_id;
    }

    public void setAdm_id(String adm_id) {
        this.adm_id = adm_id;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
