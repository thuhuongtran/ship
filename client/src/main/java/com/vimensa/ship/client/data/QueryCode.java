package com.vimensa.ship.client.data;

public class QueryCode {
    public static final String REGISTER_CLIENT =
            "INSERT INTO `client`(`phone`,`code`,`enabled`,`cli_id`) VALUES(?,?,?,?)";
    public static final String LOGIN_LOG = "INSERT INTO `user_log`(`user_id`,`time_in`,`timestmp`,`role`) VALUES(?,?,?,?)";
    public static final String GET_CLIENT_BY_PHONE =
            "SELECT * FROM `client` WHERE `phone` =?";
    public static final String GET_SHIPPER_BY_PHONE =
            "SELECT * FROM `shipper` WHERE `phone` =?";

    public static final String ADD_NEW_ORDER = "INSERT INTO `order`(`od_id`,`created_time`,`cli_id`,`adv_paym`," +
            "`item_type`,`note`,`from`,`from_lat`,`from_log`,`custm_phone`,`distance`,`fee`,`wait_time`,`status`) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String ADD_NEW_ORDER_SYSTEM = "INSERT INTO `order_system`(`od_id`,`timestmp`,`status`)" +
            "VALUES(?,?,?)";
    public static final String ADD_NEW_DESTINATION = "INSERT INTO `destinations`(`od_id`,`to_lat`,`to_log`,`to`)" +
            " VALUES(?,?,?,?)";
    public static final String DELETE_ORDER_SYSTEM = "DELETE FROM `order_system` WHERE `od_id`=?";
    public static final String DELETE_ORDER_SHIPPER = "DELETE FROM `order_shipper` WHERE `od_id`=?";
    public static final String GET_SHIPPER_ACPETED_ORDER = "SELECT `shipper`.`phone`,`shipper`.`name`,`shipper`.`shp_id`," +
            "`shipper`.`star`,`shipper`.`avatar` FROM `shipper` INNER JOIN `order_shipper` WHERE `shipper`.`shp_id`=`order_shipper`.`shp_id`" +
            "AND `order_shipper`.`od_id`=? AND `order_shipper`.`status`=?";
    public static final String GET_ORDER_BY_ORDER_ID = "SELECT `order`.`od_id`,`order`.`created_time`,`order`.`cli_id`," +
            "`order`.`shp_id`,`order`.`adv_paym`,`order`.`item_type`,`order`.`note`,`order`.`from`,`order`.`custm_phone`," +
            "`order`.`distance`,`order`.`fee`,`order`.`wait_time` FROM `order` WHERE `od_id`=?";
    public static final String GET_DESTINATIONS_BY_OD_ID = "SELECT `to`,`to_lat`,`to_log` FROM `destinations` " +
            " WHERE `od_id`=?";
    public static final String GET_ALL_ORDER_STATUS_BY_ORDER_ID = "SELECT `timestmp`,`status` FROM `order_log` WHERE " +
            " `od_id`=?";
}
