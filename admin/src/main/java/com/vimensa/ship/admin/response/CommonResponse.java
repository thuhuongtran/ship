package com.vimensa.ship.admin.response;

public class CommonResponse extends BaseResponse{
    private int error;

    public CommonResponse() {
    }

    public CommonResponse(int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
