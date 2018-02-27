package com.vimensa.ship.client.controller;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.dao.Shipper;
import com.vimensa.ship.client.data.DataProcess;
import com.vimensa.ship.client.model.ErrorCode;
import com.vimensa.ship.client.request.NewOrderRequest;
import com.vimensa.ship.client.request.Phone;
import com.vimensa.ship.client.response.CommonResponse;
import com.vimensa.ship.client.response.NewOrderResponse;
import com.vimensa.ship.client.service.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    /**
     * /newOrder: new order to process immediately
     * add into order_system
     * call get_shipper_api to response to shipper
     * insert into order_system
     * log
     * @param: from - to - note - mass - adv_paym
     * */
    @RequestMapping(value = "/neworder",method = RequestMethod.POST)
    @ResponseBody
    public NewOrderResponse newOrder(
                         @RequestBody NewOrderRequest newOrder,
                         @RequestHeader("Authorization")String jwt){
        NewOrderResponse resp = new NewOrderResponse();
        try {
            long timestamp =Calendar.getInstance().getTimeInMillis();
            String orderID = timestamp+"OD";
            double fee = Tasks.getFee(newOrder.getDistance());
            // add into order_system
            dao.newOrderSystem(timestamp,orderID,fee,newOrder);
            // call system to get driver
            resp = Tasks.getDriver(newOrder,orderID,jwt);
            // call db to get shipper info
            Shipper shipper = dao.getShipperByPhone(resp.getShipper_phone());
            resp.setShipper_name(shipper.getName());
            resp.setStar(shipper.getStar());
            resp.setFee(fee);
            resp.setError(ErrorCode.SUCCESS);
        } catch (IOException e) {
            resp.setError(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName()+" io exception");
            e.printStackTrace();
        }
        return resp;
    }
    @RequestMapping(value = "/sessionLog",method = RequestMethod.POST)
    public CommonResponse sessionLog(@RequestBody Phone p){
        CommonResponse res = new CommonResponse();
        String phone = p.getPhone();
        dao.clientLoginLog(phone);
        res.setError(ErrorCode.SUCCESS);
        logger.info(Controller.class.getName()+" session log successfully.");
        return res;
    }

}
