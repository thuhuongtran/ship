package com.vimensa.ship.shipper.model;

public class Status {
    public static final int UNENABLED_SHIPPER = 0;
    public static final int ENABLED_SHIPPER = 1;
    public static final String SHIPPER_ROLE = "shipper";

    // status in the table order_shipper
    public static final int SHIPPER_ACCEPT_ORDER = 0;
    public static final int WAIT_SHIPPER_DECISION = 2;

    // status in the table shipper_system
    public static final int SHIPPER_SLEEPING = 5;
    public static final int SHIPPER_AWAKE = 6;
    public static final int SHIPPER_ON_WAY = 7;

    // status in the table order_log
    public static final int ORDER_WAITING_TAKE_OVER = 8;
    public static final int ORDER_DELIVERING = 9;
    public static final int ORDER_DELIVERED = 10;
    public static final int ORDER_CANCELED = 11;
    public static final int ORDER_DELIVERD_UNSUCCESSFULLY = 12;

    // status in order_system
    public static final int URGENT_ORDER = 4;
    public static final int WAIT_ORDER = 3;

    // status in order
    public static final int UNSUCCESSFUL = 13;
    public static final int SUCCESSFUL =14;
}
