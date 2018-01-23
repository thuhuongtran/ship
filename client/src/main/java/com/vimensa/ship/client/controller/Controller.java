package com.vimensa.ship.client.controller;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.data.DataProcessImp;
import com.vimensa.ship.client.model.ErrorCode;
import com.vimensa.ship.client.service.LoginCode;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(APIStart.class);
    @Autowired
    private DataProcessImp dao;

    /**
     * /register
     * call service to get random code
     * add in db
     * @param: phone
     * */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(HttpServletRequest req, HttpServletResponse res){
        String phone = req.getParameter("phone");
        String code = LoginCode.getCode();
        dao.registerClient(phone,code);
        logger.info(Controller.class.getName()+" insert into client successfully");
        res.addHeader("e:", String.valueOf(ErrorCode.SUCCESS));
    }
}
