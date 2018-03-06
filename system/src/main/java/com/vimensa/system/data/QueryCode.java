package com.vimensa.system.data;

public class QueryCode {
    public static final String GET_ALL_AWAKE_DRIVERS = "SELECT * FROM `shipper_system` WHERE `status`=?";
    public static final String GET_SHIPPER_BY_PHONE = "SELECT * FROM `shipper_system` WHERE `phone`=?";
    public static final String ADD_NEW_ORDER_SHIPPER_SYSTEM = "INSERT INTO `order_shipper`" +
            "(`timestamp`,`order_id`,`shipper_phone`,`status`) VALUES(?,?,?,?)";
    public static final String GET_ALL_ORDER_SYSTEM ="SELECT `order_id`,`from_lat`,`from_log`,`to_lat`,`to_log`,`wait_time` " +
            "FROM `order_system` WHERE `status`=?";
    public static final String GET_EARLIEST_DELIVERY_NEEDED_URGENT_ORDER = "SELECT `order_id`,`from_lat`,`from_log`,`to_lat`,`to_log`,min(`wait_time`) as wait_time"+
            " FROM `order_system` WHERE `status`=?";
    public static final String DELETE_HANDLED_ORDER_SYSTEM = "DELETE FROM `order_system` WHERE `order_id`=?";
    public static final String CHANGE_STATUS_TO_WAIT_SHIPPER_DECISION_ORDER_SYSTEM = "UPDATE `order_system` SET `status`=?" +
            " WHERE `order_id`=?";
    public static final String CHANGE_WAIT_ORDER_STATUS_TO_URGENT_ORDER_SYSTEM = "UPDATE `order_system` SET `status`=? " +
            " WHERE DATE(FROM_UNIXTIME(`wait_time`*0.001))=CURDATE() AND `status`=?";
}
