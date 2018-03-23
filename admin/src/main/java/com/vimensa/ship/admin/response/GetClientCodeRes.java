package com.vimensa.ship.admin.response;

public class GetClientCodeRes extends BaseResponse{
    private String code;
    private int error;

    public GetClientCodeRes() {
    }

    public GetClientCodeRes(String code, int error) {
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
