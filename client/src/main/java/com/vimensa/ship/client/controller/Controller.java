package com.vimensa.ship.client.controller;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.data.DataProcess;
import com.vimensa.ship.client.model.ErrorCode;
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
        res.addHeader("e:", String.valueOf(ErrorCode.SUCCESS));
    }
    /**
     * /newOrder
     * new order to process immediately
     * @param: from - to - note - mass - adv_paym
     * */
    @RequestMapping(value = "/neworder",method = RequestMethod.POST)
    public void newOrder(HttpServletRequest req, HttpServletResponse res,
                         @RequestParam("from") String from,
                         @RequestParam("to")String to,
                         @RequestParam("note") String note,
                         @RequestParam("mass")String mass,
                         @RequestParam("adv_paym")String adv_paym,
                         @RequestHeader("Authorization")String jwt){
        from = req.getParameter("from");
        to = req.getParameter("to");
        note = req.getParameter("note");
        mass = req.getParameter("mass");
        adv_paym = req.getParameter("adv_paym");
        jwt = req.getHeader("Authorization");
        logger.info("param: "+from+" "+to+" "+note+" "+" "+mass+" "+adv_paym);
        logger.info(jwt);
        tasks.addNewOrder(from,to,mass,adv_paym,note,jwt);
        //call to get best suitable shipper
        logger.info(Controller.class.getName()+" insert successfully");
        res.addHeader("e:", String.valueOf(ErrorCode.SUCCESS));
    }
}
