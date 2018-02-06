package com.vimensa.ship.client.data;

public class QueryCode {
    public static final String REGISTER_CLIENT =
            "INSERT INTO `client`(`phone`,`code`,`enabled`) VALUES(?,?,0)";
    public static final String GET_CLIENT_BY_PHONE =
            "SELECT * FROM `client` WHERE `phone` =?";
    public static final String GET_SHIPPER_BY_PHONE =
            "SELECT * FROM `shipper` WHERE `phone` =?";

    public static final String NEW_ORDER_LOG = "INSERT INTO `order_log`" +
            "(`order_id`,`timestamp`,`status`,`client_phone`,`shipper_phone`,`adv_paym`,`mass`,`note`,`from`,`to`,`distance`,`fee`)" +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
}
