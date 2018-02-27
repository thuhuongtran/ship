package com.vimensa.ship.shipper.authentication.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vimensa.ship.shipper.APIStart;
import com.vimensa.ship.shipper.controller.Controller;
import com.vimensa.ship.shipper.data.DataProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter{

    private final Logger logger = LoggerFactory.getLogger(APIStart.class);


    public JWTLoginFilter(String url, AuthenticationManager authManager){
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {
        AccountCredentials creds = new ObjectMapper()
                .readValue(req.getInputStream(), AccountCredentials.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getPhone(),
                        creds.getCode(),
                        Collections.emptyList()
                )
        );
    }
    @Override
    protected void successfulAuthentication(
            HttpServletRequest req, HttpServletResponse res,
            FilterChain chain, Authentication auth)
            throws IOException,ServletException{
        TokenAuthenticationService
                .addAuthentication(res, auth.getName());
        // session log

    }
}
