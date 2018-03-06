package com.vimensa.system.service;

import com.vimensa.system.dao.Order;
import com.vimensa.system.data.DataProcess;
import com.vimensa.system.security.TokenAuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Tasks {
    @Autowired
    private DataProcess dao;

    private static final Logger log = LoggerFactory.getLogger(Tasks.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static boolean checkAdminRole(String jwt){
        Claims claims = Jwts.parser().setSigningKey(TokenAuthenticationService.SECRET).parseClaimsJws(jwt).getBody();
        String body = claims.getSubject();
        return body.contains("aDm");
    }
    /**
     * find the nearest shipper
     * add in order_shipper db
     * then change status in order_system to wait_shipper_decision handled order
     * */
    @Scheduled(fixedRate = 45000)
    public void handleUrgentOrders(){
        log.info("Handle urgent orders: The time is now {}", dateFormat.format(new Date()));
        Order ord = dao.getEarliestDeliveryNeededUrgentOrder();
        if(ord==null){
            log.info("The order is over.");
        }
        else{
            try {
                String shipperPhone= OrderProcess.getDriver(ord, OrderProcess.toDrivers(dao.findAllDrivers()));
                dao.addNewOrderShipperSystem(shipperPhone,ord.getOrder_id());
                dao.changeStatusToWaitShipperDecisionOrderSystem(ord.getOrder_id());
                log.info("Handle urgent orders: successfully.");
            } catch (IOException e) {
                log.info(" IO exception: call ggl to get distance.");
                e.printStackTrace();
            }
        }

    }
    /**
     * check handle-day of wait_order then turn to urgent_order
     * */
    @Scheduled(fixedRate = 24*60*60*1000)
    public void handleWaitOrder(){
        log.info("The time is now {}", dateFormat.format(new Date()));
        dao.changeWaitOrderStatusToUrgentOrderSystem();
        log.info("Wait order to urgent after each day: successfully.");
    }
}