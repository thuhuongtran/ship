package com.vimensa.ship.shipper.model;

public class Status {
    public static final int UNENABLED_SHIPPER = 0;
    public static final int ENABLED_SHIPPER = 1;
    public static final String SHIPPER_ROLE = "shipper";

    // status in the table order_shipper
    public static final int SHIPPER_ACCEPT_ORDER = 0;
    public static final int SHIPPER_REFUSE_ORDER =1;
    public static final int WAIT_SHIPPER_DECISION = 2;

    // status in the table shipper_system
    public static final int SHIPPER_SLEEPING = 5;
    public static final int SHIPPER_AWAKE = 6;
    public static final int SHIPPER_ON_WAY = 7;
}
