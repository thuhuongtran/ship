package com.vimensa.ship.shipper.service;

import java.util.Random;

public class LoginCode {
    /**
     * create code send to user to login by phone number
     * */
    public static String getCode(){
        String str = "";
        Random rand = new Random();
        int  n = rand.nextInt(999999) + 100000;
        //999999 is the maximum and the 100000 is our minimum
        return str+n;
    }
}
