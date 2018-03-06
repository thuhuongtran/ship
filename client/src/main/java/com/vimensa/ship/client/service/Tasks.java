package com.vimensa.ship.client.service;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.authentication.security.TokenAuthenticationService;
import com.vimensa.ship.client.data.DataProcess;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class Tasks {
    private final static Logger logger = LoggerFactory.getLogger(APIStart.class);
    @Autowired
    private DataProcess dao;

    public static String getDecodedJwt(String jwt) {
        String result = "";

        String[] parts = jwt.split("[.]");
        try {
            int index = 0;
            for (String part : parts) {
                if (index >= 2)
                    break;

                index++;
                byte[] partAsBytes = part.getBytes("UTF-8");
                String decodedPart = new String(java.util.Base64.getUrlDecoder().decode(partAsBytes), "UTF-8");

                result += decodedPart;
            }
        } catch (Exception e) {
            throw new RuntimeException("Couldn't decode jwt", e);
        }
        result = result.substring(result.indexOf("sub") + 6, result.indexOf(",") - 1);
        return result;
    }
    public static double getFee(double distance) { // distance in km
        double fee = 20000;
        if (distance <= 3) {
            fee = 20000;
        } else {
            int mul = (int) (distance / 2);
            fee = fee + mul * 5000;
        }
        return fee;
    }


    public static boolean checkClientRole(String jwt){
        Claims claims = Jwts.parser().setSigningKey(TokenAuthenticationService.SECRET).parseClaimsJws(jwt).getBody();
        String body = claims.getSubject();
        return body.contains("cLi");
    }
    public static String formatDate(Date d){
        return new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(d).toString();
    }

}
