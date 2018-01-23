package com.vimensa.ship.client.controller;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.model.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(APIStart.class);

    /**
     * blank page redirect to login page
     * */
    @RequestMapping(value = {"","/"}, method = RequestMethod.GET)
    public void home(HttpServletResponse response){
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            response.addHeader("e:", String.valueOf(ErrorCode.SYSTEM_EXCEPTION));
            logger.info(Controller.class.getName()+" /home -"+e.getMessage());
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletResponse response){
        return "login";
    }
}
