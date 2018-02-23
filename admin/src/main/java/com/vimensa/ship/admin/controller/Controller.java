package com.vimensa.ship.admin.controller;

import com.vimensa.ship.admin.APIStart;
import com.vimensa.ship.admin.request.AcceptNewShipper;
import com.vimensa.ship.admin.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
public class Controller {
    private final Logger logger = LoggerFactory.getLogger(APIStart.class);

    /**
     * accept new register-shipper
     * change enable in shipper to 1 and add in user_role
     * @param newShipper
     * */
    @RequestMapping(value = "/acceptShipper", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse acceptShipper(@RequestBody AcceptNewShipper newShipper){
        String shipperPhone = newShipper.getShipper_phone();
        long timestamp = Calendar.getInstance().getTimeInMillis();
    }
}
