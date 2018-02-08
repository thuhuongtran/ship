package com.vimensa.ship.shipper.controller;

import com.vimensa.ship.shipper.APIStart;
import com.vimensa.ship.shipper.data.DataProcess;
import com.vimensa.ship.shipper.model.ErrorCode;
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
     * get new order from order_shipper on db system
     * call each 1 min time.
     * */
    @RequestMapping(value = "/getorder",method = RequestMethod.POST)
    @ResponseBody
    public GetOrder getOrder(@RequestBody String phone){
        GetOrder ord = new GetOrder();
        int count = dao.countOrderShipperSystem(phone);
        if(count<=0){
            ord.setError(ErrorCode.NO_ORDER);
        }
        else if(count > 0){
            ord = dao.getDetailOrderSystem(phone);
            ord.setError(ErrorCode.SUCCESS);
        }
        return  ord;
    }

}