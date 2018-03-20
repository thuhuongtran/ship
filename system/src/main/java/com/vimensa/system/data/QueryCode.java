package com.vimensa.system.data;

public class QueryCode {
    public static final String GET_ALL_AWAKE_SHIPPERS = "SELECT * FROM `shipper_system` WHERE `status`=?";
    public static final String GET_SHIPPER_BY_PHONE = "SELECT * FROM `shipper_system` WHERE `phone`=?";
    public static final String ADD_NEW_ORDER_SHIPPER_SYSTEM = "INSERT INTO `order_shipper`" +
            "(`od_id`,`shp_id`,`status`,`timestmp`) VALUES(?,?,?,?)";
    public static final String GET_EARLIEST_DELIVERY_NEEDED_URGENT_ORDER = "SELECT `order_system`.`od_id`,`order`.`from_lat`," +
            "`order`.`from_log`,min(`order`.`wait_time`) as wait_time FROM `order_system` INNER JOIN `order` " +
            "WHERE `order_system`.`od_id`=`order`.`od_id` AND `order_system`.`status`=?";
    public static final String DELETE_HANDLED_ORDER_SYSTEM = "DELETE FROM `order_system` WHERE `order_id`=?";
    public static final String CHANGE_STATUS_TO_WAIT_SHIPPER_DECISION_ORDER_SYSTEM = "UPDATE `order_system` SET `status`=?" +
            " WHERE `od_id`=?";
    public static final String CHANGE_STATUS_TO_WAIT_SHIPPER_DECISION_SHIPPER_SYSTEM = "UPDATE `shipper_system` SET `status`=?" +
            " WHERE `shp_id`=?";
    public static final String CHANGE_WAIT_ORDER_STATUS_TO_URGENT_ORDER_SYSTEM = "UPDATE `order_system` INNER JOIN `order`" +
            " ON `order`.`od_id`=`order_system`.`od_id` AND `order_system`.`status`=?" +
            " SET `order_system`.`status` = ? WHERE DATE(FROM_UNIXTIME(`wait_time`*0.001))=CURDATE()";
}
