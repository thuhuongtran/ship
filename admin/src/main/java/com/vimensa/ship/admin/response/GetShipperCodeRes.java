package com.vimensa.ship.admin.response;

public class GetShipperCodeRes extends BaseResponse{
    private String code;
    private int error;

    public GetShipperCodeRes() {
    }

    public GetShipperCodeRes(String code, int error) {
        this.code = code;
        this.error = error;
    }

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
