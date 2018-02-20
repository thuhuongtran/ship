package com.vimensa.ship.shipper.response;

public class CommonResponse {
    private int error;

    public CommonResponse(int error) {
        this.error = error;
    }

    public CommonResponse() {
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }
}
