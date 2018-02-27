package com.vimensa.ship.shipper.data;

public class QueryCode {
    public static final String COUNT_ORDER_SHIPPER_SYSTEM_BY_PHONE = "SELECT COUNT(*) FROM `order_shipper` WHERE `shipper_phone`=? AND `status`=2";
    public static final String GET_DETAIL_ORDER_SYSTEM = " SELECT `order_system`.`client_phone`,`order_system`.`client_name`,`order_system`.`adv_paym`,\n" +
            " `order_system`.`mass`,`order_system`.`note`,`order_system`.`from`,`order_system`.`to`,\n" +
            " `order_system`.`distance`,`order_system`.`fee`,`order_system`.`item_type` \n" +
            " FROM `order_system`\n" +
            " INNER JOIN `order_shipper` ON `order_system`.`order_id`= `order_shipper`.`order_id`\n" +
            " WHERE `order_shipper`.`shipper_phone`=? AND `order_shipper`.`status`=2;";
    public static final String REGISTER_SHIPPER = "INSERT INTO `shipper`(`phone`,`code`,`enabled`) VALUES(?,?,?)";
    public static final String LOGIN_LOG = "INSERT INTO `user_log`(`phone`,`time_in`,`timestamp`,`role`) VALUES(?,?,?,?)";

}
