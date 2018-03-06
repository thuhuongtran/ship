package com.vimensa.ship.shipper.controller;

import com.vimensa.ship.shipper.APIStart;
import com.vimensa.ship.shipper.dao.OrderSystem;
import com.vimensa.ship.shipper.data.DataProcess;
import com.vimensa.ship.shipper.model.ErrorCode;
import com.vimensa.ship.shipper.request.AcceptOrder;
import com.vimensa.ship.shipper.request.Phone;
import com.vimensa.ship.shipper.response.CommonResponse;
import com.vimensa.ship.shipper.response.GetOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(APIStart.class);
    @Autowired
    private DataProcess dao;

    /**
     * new shipper register
     * add in shipper table with enable = 0
     * @param: phone
     * */
    @RequestMapping(value ="/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse register(@RequestBody Phone register){
        CommonResponse res = new CommonResponse();
        String phone = register.getPhone();
        dao.registerNewShipper(phone);
        res.setError(ErrorCode.SUCCESS);
        logger.info(Controller.class.getName()+" register new shipper successfully.");
        return res;
    }
    @RequestMapping(value = "/sessionLog",method = RequestMethod.POST)
    public CommonResponse sessionLog(@RequestBody Phone p){
        CommonResponse res = new CommonResponse();
        String phone = p.getPhone();
        dao.shipperLoginLog(phone);
        res.setError(ErrorCode.SUCCESS);
        logger.info(Controller.class.getName()+" session log successfully.");
        return res;
    }

    /**
     * get new order from order_shipper on db system ------NOT TESTED YET
     * call each 45s time.
     * */
    @RequestMapping(value = "/getorder",method = RequestMethod.POST)
    @ResponseBody
    public GetOrder getOrder(@RequestBody Phone p){
        String phone = p.getPhone();
        GetOrder ord = new GetOrder();
        int count = dao.countWaitAcceptingOrderShipperSystem(phone);
        if(count<=0){
            ord.setError(ErrorCode.NO_ORDER);
        }
        else{
            ord = dao.getDetailOrderSystem(phone);
            ord.setError(ErrorCode.SUCCESS);
        }
        return  ord;
    }
    /**
     * change status in order_shipper to SHIPPER_ACCEPT_ORDER
     * change status in shipper_system to ON_WAY
     * add in order_log with status = WAITING_TAKE_OVER
     * */
    @RequestMapping(value = "/acceptorder",method = RequestMethod.POST)
    @ResponseBody
    public void acceptOrder(@RequestBody AcceptOrder ao){
        String shipper_phone = ao.getPhone();
        String order_id = ao.getOrder_id();
        dao.changeStatusInOrderShipperToAccepted(order_id);
        dao.changeShipperStatusToOnWayInShipperSystem(shipper_phone);
        OrderSystem o = dao.getOrderSystemByOrderID(order_id);
        dao.addNewOrderLog(o,shipper_phone);
    }
}
