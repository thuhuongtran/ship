package com.vimensa.ship.admin.data;

public class QueryCode {
    public final static String ACCEPT_NEW_REGISTER_SHIPPER_ENABLE = "UPDATE `shipper` SET `name`=?,`mail`=?,`enabled`=?,`timestamp`=?" +
            "WHERE `phone`=?;";
    public final static String ACCEPT_NEW_REGISTER_CLIENT_ENABLE = "UPDATE `client` SET `name`=?,`mail`=?,`enabled`=?,`timestamp`=?" +
            "WHERE `phone`=?;";
    public final static String GET_SHIPPER_BY_PHONE = "SELECT * FROM `shipper` WHERE `phone`=?";
    public final static String ADD_INTO_USER_ROLE = "INSERT INTO `user_role`(`phone`,`role`) VALUES(?,?)";
    public final static String GET_ALL_UNENABLED_SHIPPERS = "SELECT `phone` FROM `shipper` WHERE `enabled`=?";
    public final static String GET_CLIENT_BY_PHONE = "SELECT * FROM `client` WHERE `phone`=?";
    public static final String LOGIN_LOG = "INSERT INTO `user_log`(`phone`,`time_in`,`timestamp`,`role`) VALUES(?,?,?,?)";


}
