package com.vimensa.ship.shipper.response;

public abstract class BaseResponse {
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
