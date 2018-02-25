package com.vimensa.ship.admin.response;

public class GetClientCodeRes {
    private String code;
    private int error;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }
}
