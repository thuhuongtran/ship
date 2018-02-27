package com.vimensa.ship.admin.service;

import com.vimensa.ship.admin.authentication.security.TokenAuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class Tasks {
    public static boolean checkAdminRole(String jwt){
        Claims claims = Jwts.parser().setSigningKey(TokenAuthenticationService.SECRET).parseClaimsJws(jwt).getBody();
        String body = claims.getSubject();
        return body.contains("aDm");
    }
}
