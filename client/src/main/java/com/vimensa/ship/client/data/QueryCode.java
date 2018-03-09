package com.vimensa.ship.client.data;

public class QueryCode {
    public static final String REGISTER_CLIENT =
            "INSERT INTO `client`(`phone`,`code`,`enabled`,`cli_id`) VALUES(?,?,?,?)";
    public static final String LOGIN_LOG = "INSERT INTO `user_log`(`user_id`,`time_in`,`timestmp`,`role`) VALUES(?,?,?,?)";
    public static final String GET_CLIENT_BY_PHONE =
            "SELECT * FROM `client` WHERE `phone` =?";
    public static final String GET_SHIPPER_BY_PHONE =
            "SELECT * FROM `shipper` WHERE `phone` =?";

    public static final String ADD_NEW_ORDER_SYSTEM =
            "INSERT INTO `order_system`(`order_id`,`from_lat`,`from_log`,`to_lat`,`to_log`,`timestamp`,`status`," +
                    "`client_phone`,`adv_paym`,`mass`,`note`,`from`,`to`,`distance`,`fee`,`item_type`,`wait_time`)" +
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

}
