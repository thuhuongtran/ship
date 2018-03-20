package com.vimensa.ship.shipper.controller;

import com.vimensa.ship.shipper.APIStart;
import com.vimensa.ship.shipper.dao.Shipper;
import com.vimensa.ship.shipper.data.DataProcess;
import com.vimensa.ship.shipper.model.Destination;
import com.vimensa.ship.shipper.model.ErrorCode;
import com.vimensa.ship.shipper.model.Order;
import com.vimensa.ship.shipper.request.AcceptOrder;
import com.vimensa.ship.shipper.request.Phone;
import com.vimensa.ship.shipper.request.ShipperID;
import com.vimensa.ship.shipper.response.CommonResponse;
import com.vimensa.ship.shipper.response.GetOrder;
import com.vimensa.ship.shipper.response.ShipperInfoRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ShipperInfoRes sessionLog(@RequestBody Phone p){
        String phone = p.getPhone();
        Shipper s = dao.getShipperByPhone(phone);
        dao.shipperLoginLog(s.getShp_id());
        ShipperInfoRes res = new ShipperInfoRes(s.getPhone(), s.getCode(), s.getName(), s.getMail(), s.getShp_id(), s.getStar(), s.getAvatar());
        res.setError(ErrorCode.SUCCESS);
        logger.info(Controller.class.getName()+" session log successfully.");
        return res;
    }

    /**
     * get new order from order_shipper on db system ------NOT TESTED YET -WRONG
     * call each 45s time.
     * */
    @RequestMapping(value = "/getorder",method = RequestMethod.POST)
    @ResponseBody
    public GetOrder getOrder(@RequestBody ShipperID shp){
        String shp_id = shp.getShp_id();
        int count = dao.countWaitAcceptingOrderShipperSystem(shp_id);
        GetOrder res = new GetOrder();
        if(count<=0){
            res.setError(ErrorCode.NO_ORDER);
        }
        else{
            Order ord = dao.shipperGetDetailOrderByShipperID(shp_id);
            List<Destination> li = dao.getDestinationsByODID(ord.getOd_id());
            res = new GetOrder(ord, li);
            res.setError(ErrorCode.SUCCESS);
        }
        return  res;
    }
    /**
     * change status in order_shipper to SHIPPER_ACCEPT_ORDER
     * change status in shipper_system to ON_WAY
     * add in order_log with status = WAITING_TAKE_OVER
     *  NOT DONE - RESPONSE ------------------------------------------------------
     * */
    @RequestMapping(value = "/acceptorder",method = RequestMethod.POST)
    @ResponseBody
    public void acceptOrder(@RequestBody AcceptOrder ao){
        String shipper_phone = ao.getPhone();
        String order_id = ao.getOrder_id();
        dao.changeStatusInOrderShipperToAccepted(order_id);
        dao.changeShipperStatusToOnWayInShipperSystem(shipper_phone);
       // OrderSystem o = dao.getOrderSystemByOrderID(order_id);
       // dao.addNewOrderLog(o,shipper_phone);
    }
}
