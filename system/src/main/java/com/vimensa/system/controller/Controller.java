package com.vimensa.system.controller;

import com.vimensa.system.RunAPI;
import com.vimensa.system.dao.Order;
import com.vimensa.system.dao.Shipper;
import com.vimensa.system.data.DataProcess;
import com.vimensa.system.model.ErrorCode;
import com.vimensa.system.response.ShipperResponse;
import com.vimensa.system.service.OrderProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(RunAPI.class);
    @Autowired
    private DataProcess dao;

    /**
     * response shipper info
     * insert into order_shipper table db
     * */
    @RequestMapping(value = "/getshipper", method = RequestMethod.POST)
    @ResponseBody
    public ShipperResponse getShipper(@RequestParam("client_phone") String phone,
                                      @RequestParam("order_id")String orderID,
                                      @RequestParam("from_lat")double from_lat,
                                      @RequestParam("from_log") double from_log,
                                      @RequestParam("to_lat")double to_lat,
                                      @RequestParam("to_log") double to_log){
        ShipperResponse resp = new ShipperResponse();
        Order order = new Order(phone,from_lat,from_log,to_lat,to_log,0);
        try {
            String shipperPhone = OrderProcess.getDriver(order, OrderProcess.toDrivers(dao.findAllDrivers()));
            Shipper shipper = dao.getShipperByPhone(shipperPhone);
            resp.setShipperPhone(shipperPhone);
            resp.setShipperLat(shipper.getLat());
            resp.setShipperLog(shipper.getLog());
            resp.setError(ErrorCode.SUCCESS);

            //insert into db
            dao.addNewOrderShipperSystem(shipperPhone,orderID);
        } catch (IOException e) {
            resp.setError(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName()+" io exception");
            e.printStackTrace();

        }
        return resp;
    }

}
