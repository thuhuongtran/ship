package com.vimensa.ship.client.data;

public class QueryCode {
    public static final String REGISTER_CLIENT =
            "INSERT INTO `client`(`phone`,`code`,`enabled`) VALUES(?,?,0)";
    public static final String GET_CLIENT_BY_PHONE =
            "GET * FROM `client` WHERE `phone` =";
    public static final String INSERT_NEW_ORDER = "INSERT INTO `order`" +
            "(`from`,`to`,`advance_payment`,`mass`,`note`,`status`,`client_phone`,`timestamp`)" +
            "VALUES(?,?,?,?,?,?,?,?)";

}
