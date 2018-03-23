package com.vimensa.ship.admin.response;

public abstract class BaseResponse {
    private int error;

    public BaseResponse() {
    }

    public BaseResponse(int error) {
        this.error = error;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}

