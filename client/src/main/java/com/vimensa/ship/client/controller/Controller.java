package com.vimensa.ship.client.controller;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.dao.Client;
import com.vimensa.ship.client.data.DataProcess;
import com.vimensa.ship.client.model.ErrorCode;
import com.vimensa.ship.client.request.Phone;
import com.vimensa.ship.client.request.UrgentOrderRequest;
import com.vimensa.ship.client.request.WaitOrderRequest;
import com.vimensa.ship.client.response.ClientInfoRes;
import com.vimensa.ship.client.response.CommonResponse;
import com.vimensa.ship.client.service.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

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
    @ResponseBody
    public CommonResponse register(@RequestBody Phone register){
        CommonResponse res = new CommonResponse();
        String phone = register.getPhone();
        dao.registerClient(phone);
        logger.info(Controller.class.getName()+" insert into client successfully");
        res.setError(ErrorCode.SUCCESS);
        return res;
    }

    @RequestMapping(value = "/sessionLog",method = RequestMethod.POST)
    public ClientInfoRes sessionLog(@RequestBody Phone p){
        String phone = p.getPhone();
        Client cli = dao.getClientByPhone(phone);
        dao.clientLoginLog(cli.getCli_id());
        ClientInfoRes res = new ClientInfoRes(phone, cli.getCode(), cli.getName(), cli.getMail(), cli.getPlace(), cli.getCli_id(), cli.getAvatar());
        res.setError(ErrorCode.SUCCESS);
        logger.info(Controller.class.getName()+" session log successfully.");
        return res;
    }
    /**
     * urgent order
     * insert into order with status = unsuccessful
     * insert into destinations
     * insert into order_system with status = urgent_order
     * @param: urgent-order-info
     * */
    @RequestMapping(value = "/urgentorder",method = RequestMethod.POST)
    public CommonResponse urgentOrder(@RequestBody UrgentOrderRequest ord){
        String od_id = Calendar.getInstance().getTimeInMillis()+"OD";
        dao.addNewUrgentOrder(ord,od_id);
        dao.addDestinations(ord.getToLi(),od_id);
        dao.addUrgentOrderSystem(od_id);
        CommonResponse res = new CommonResponse();
        res.setError(ErrorCode.SUCCESS);
        return res;
    }
    /**
     * wait order
     * insert into order with status = unsuccessful
     * insert into destinations
     * insert into order_system with status = wait_order
     * @param: wait-order-info
     * */
    @RequestMapping(value = "/waitorder",method = RequestMethod.POST)
    public CommonResponse waitOrder(@RequestBody WaitOrderRequest ord){
        String od_id = Calendar.getInstance().getTimeInMillis()+"OD";
        dao.addNewWaitOrder(ord,od_id);
        dao.addDestinations(ord.getToLi(),od_id);
        dao.addWaitOrderSystem(od_id);
        CommonResponse res = new CommonResponse();
        res.setError(ErrorCode.SUCCESS);
        return res;
    }

}
