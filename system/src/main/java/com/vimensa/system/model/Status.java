package com.vimensa.system.model;

public class Status {

    // status in the table order_shipper + order_system
    public static final int SHIPPER_ACCEPT_ORDER=0;
    public static final int SHIPPER_REFUSE_ORDER=1;
    public static final int WAIT_SHIPPER_DECISION=2;

    // status in the table order_system
    public static final int URGENT_ORDER = 4;
    public static final int WAIT_ORDER = 3;

    // status in the table shipper_system
    public static final int SHIPPER_SLEEPING = 5;
    public static final int SHIPPER_AWAKE = 6;
    public static final int SHIPPER_ON_WAY =7;

    public static int NUMBER_SHIPPER = 2;
}
