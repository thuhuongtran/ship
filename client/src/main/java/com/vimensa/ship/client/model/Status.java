package com.vimensa.ship.client.model;

public class Status {
    public static final int CLIENT_OFFICIAL=0;
    public static final int CLIENT_UNOFFICIAL=1;
    public static final int ORDER_WAITING_FOR_CONFIRMATION = 1;
    public static final int ORDER_WAITING_PROCESSING =2;
    public static final int ORDER_WAITING_TAKE_OVER=3;
    public static final int ORDER_DELIVERING=4;
    public static final int ORDER_DELIVERED=0;
    public static final int ORDER_CANCELED=5;
}
