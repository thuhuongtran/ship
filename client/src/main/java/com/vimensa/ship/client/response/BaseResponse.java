package com.vimensa.ship.client.response;

public abstract class BaseResponse {
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
