package com.vimensa.get_shipper.controller;

import com.vimensa.get_shipper.RunAPI;
import com.vimensa.get_shipper.data.DataProcess;
import com.vimensa.get_shipper.model.ErrorCode;
import com.vimensa.get_shipper.model.Order;
import com.vimensa.get_shipper.response.ShipperResponse;
import com.vimensa.get_shipper.service.OrderProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(RunAPI.class);
    @Autowired
    private DataProcess dao;

    @RequestMapping(value = "/getshipper", method = RequestMethod.POST)
    @ResponseBody
    public ShipperResponse getShipper(@RequestParam("client_phone") String phone,
                                      @RequestParam("from_lat")double from_lat,
                                      @RequestParam("from_log") double from_log,
                                      @RequestParam("to_lat")double to_lat,
                                      @RequestParam("to_log") double to_log){
        ShipperResponse resp = new ShipperResponse();
        Order order = new Order(phone,from_lat,from_log,to_lat,to_log);
        try {
            String shipperPhone = OrderProcess.getDriver(order, OrderProcess.toDrivers(dao.findAllDrivers()));
            resp.setShipperPhone(shipperPhone);
            resp.setError(ErrorCode.SUCCESS);
        } catch (IOException e) {
            resp.setError(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName()+" io exception");
            e.printStackTrace();

        }
        return resp;
    }

}
