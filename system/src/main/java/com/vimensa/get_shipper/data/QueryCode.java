package com.vimensa.get_shipper.data;

public class QueryCode {
    public static final String GET_ALL_DRIVERS = "SELECT * FROM `shipper_system` WHERE `status`=1";
    public static final String GET_SHIPPER_BY_PHONE = "SELECT * FROM `shipper_system` WHERE `phone`=?";
    public static final String NEW_ORDER_SHIPPER_SYSTEM = "INSERT INTO `order_shipper`" +
            "(`timestamp`,`order_id`,`shipper_phone`,`status`) VALUES(?,?,?,?)";
}
