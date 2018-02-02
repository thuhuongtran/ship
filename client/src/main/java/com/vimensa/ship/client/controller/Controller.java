package com.vimensa.ship.client.controller;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.data.DataProcess;
import com.vimensa.ship.client.model.ErrorCode;
import com.vimensa.ship.client.request.NewOrder;
import com.vimensa.ship.client.service.LoginCode;
import com.vimensa.ship.client.service.Tasks;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(APIStart.class);
    @Autowired
    private DataProcess dao;
    @Autowired
    private Tasks tasks;
    /**
     * /register
     * call service to get random code
     * add in db
     * @param: phone
     * */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(HttpServletRequest req, HttpServletResponse res,
                         @RequestParam("phone") String phone){
        phone = req.getParameter("phone");
        String code = LoginCode.getCode();
        dao.registerClient(phone,code);
        logger.info(Controller.class.getName()+" insert into client successfully");
        res.addHeader("e", String.valueOf(ErrorCode.SUCCESS));
    }
    /**
     * /newOrder
     * new order to process immediately
     * call get_shipper_api to response to shipper
     * insert into order_system
     * log
     * @param: from - to - note - mass - adv_paym
     * */
    @RequestMapping(value = "/neworder",method = RequestMethod.POST)
    public void newOrder(HttpServletRequest req, HttpServletResponse res,
                         @RequestBody NewOrder newOrder,
                         @RequestHeader("Authorization")String jwt){

        jwt = req.getHeader("Authorization");

        //insert into order_log

        // add in order_system
        //call get_shipper_api to response the most suitable shipper to client
        res.addHeader("e", String.valueOf(ErrorCode.SUCCESS));
    }


}
