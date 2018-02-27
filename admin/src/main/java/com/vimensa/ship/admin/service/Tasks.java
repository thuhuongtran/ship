package com.vimensa.ship.admin.service;

import com.vimensa.ship.admin.authentication.security.TokenAuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tasks {
    public static boolean checkAdminRole(String jwt){
        Claims claims = Jwts.parser().setSigningKey(TokenAuthenticationService.SECRET).parseClaimsJws(jwt).getBody();
        String body = claims.getSubject();
        return body.contains("aDm");
    }
    public static String formatDate(Date d){
        return new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(d).toString();
    }
}
