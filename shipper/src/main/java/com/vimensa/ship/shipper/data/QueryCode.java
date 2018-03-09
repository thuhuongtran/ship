package com.vimensa.ship.shipper.data;

public class QueryCode {
    public static final String COUNT_WAIT_ACCEPTING_ORDER_SHIPPER_SYSTEM_BY_PHONE = "SELECT COUNT(*) FROM `order_shipper` WHERE `shipper_phone`=? AND `status`=?";
    public static final String GET_DETAIL_ORDER_SYSTEM = " SELECT `order_system`.`client_phone`,`order_system`.`client_name`,`order_system`.`adv_paym`,\n" +
            " `order_system`.`mass`,`order_system`.`note`,`order_system`.`from`,`order_system`.`to`,\n" +
            " `order_system`.`distance`,`order_system`.`fee`,`order_system`.`item_type` \n" +
            " FROM `order_system`\n" +
            " INNER JOIN `order_shipper` ON `order_system`.`order_id`= `order_shipper`.`order_id`\n" +
            " WHERE `order_shipper`.`shipper_phone`=? AND `order_shipper`.`status`=?";
    public static final String REGISTER_SHIPPER = "INSERT INTO `shipper`(`phone`,`code`,`enabled`,`shp_id`) VALUES(?,?,?,?)";
    public static final String LOGIN_LOG = "INSERT INTO `user_log`(`user_id`,`time_in`,`timestmp`,`role`) VALUES(?,?,?,?)";
    public static final String CHANGE_STATUS_TO_ACCEPTED_IN_ORDER_SHIPPER = "UPDATE `order_shipper` SET `status`=? " +
            "WHERE `order_id`=? AND `status`=?";
    public static final String CHANGE_SHIPPER_STATUS_TO_ON_WAY_SHIPPER_SYSTEM="UPDATE `shipper_system` SET `status`=?" +
            " WHERE `phone`=?";
    public static final String GET_ORDER_SYSTEM_BY_ORDERID = "SELECT * FROM `order_system` WHERE `order_id`=?";
    public static final String ADD_NEW_ORDER_LOG = "INSERT INTO `order_log`(`order_id`,`timestamp`,`status`,`client_phone`," +
            "`shipper_phone`,`adv_paym`,`mass`,`note`,`from`,`to`,`distance`,`fee`,`item_type`,`created_time`,`wait_time`)" +
            "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String GET_SHIPPER_BY_PHONE = "SELECT * FROM `shipper` WHERE `phone`=?";

}
