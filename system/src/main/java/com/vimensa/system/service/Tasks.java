package com.vimensa.system.service;

import com.vimensa.system.dao.Order;
import com.vimensa.system.data.DataProcess;
import com.vimensa.system.model.Distance;
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
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
     * */
    @Scheduled(fixedRate = 60000)
    public void handleOrders(){
        log.info("The time is now {}", dateFormat.format(new Date()));
        List<Order> oLi = dao.getAllUrgentOrders();
        for(int i = 0;i<oLi.size();i++){
            try {
                OrderProcess.getDriver(oLi.get(i),OrderProcess.toDrivers(dao.findAllDrivers()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void sortOrdersByWaittingTime(List<Order> oli){
        oli.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return (int) (o1.getWait_time()-o2.getWait_time());
            }
        });
    }
}
