package com.vimensa.ship.shipper.data;

public class QueryCode {
    public static final String REGISTER_SHIPPER = "INSERT INTO `shipper`(`phone`,`code`,`enabled`,`shp_id`) VALUES(?,?,?,?)";
    public static final String GET_SHIPPER_BY_PHONE = "SELECT * FROM `shipper` WHERE `phone`=?";
    public static final String LOGIN_LOG = "INSERT INTO `user_log`(`user_id`,`time_in`,`timestmp`,`role`) VALUES(?,?,?,?)";
    public static final String COUNT_WAIT_ACCEPTING_ORDER_SHIPPER_SYSTEM_BY_SHIPPERID = "SELECT COUNT(*) FROM `order_shipper` WHERE `shp_id`=? AND `status`=?";

    public static final String SHIPPER_GET_HALF_DETAIL_ORDER = "SELECT `order`.`adv_paym`,`order`.`item_type`,`order`.`note`,`order`.`from`," +
            " `order`.`from_lat`,`order`.`from_log`,`order`.`custm_phone`,`order`.`distance`," +
            "        `order`.`fee`,`order`.`wait_time`,`order`.`od_id`" +
            " FROM `order` INNER JOIN `order_shipper` " +
            " WHERE `order_shipper`.`od_id` = `order`.`od_id` AND `order_shipper`.`shp_id` =? AND `order_shipper`.`status`=?";
    public static final String GET_DESTINATIONS_BY_OD_ID = "SELECT `to`,`to_lat`,`to_log` FROM `destinations` " +
            " WHERE `od_id`=?";

    public static final String CHANGE_STATUS_TO_ACCEPTED_IN_ORDER_SHIPPER = "UPDATE `order_shipper` SET `status`=? " +
            "WHERE `order_id`=? AND `status`=?";
    public static final String CHANGE_SHIPPER_STATUS_TO_ON_WAY_SHIPPER_SYSTEM="UPDATE `shipper_system` SET `status`=?" +
            " WHERE `phone`=?";
    public static final String GET_ORDER_SYSTEM_BY_ORDERID = "SELECT * FROM `order_system` WHERE `order_id`=?";
    public static final String ADD_NEW_ORDER_LOG = "INSERT INTO `order_log`(`order_id`,`timestamp`,`status`,`client_phone`," +
            "`shipper_phone`,`adv_paym`,`mass`,`note`,`from`,`to`,`distance`,`fee`,`item_type`,`created_time`,`wait_time`)" +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

}
