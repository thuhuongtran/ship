package com.vimensa.ship.admin.controller;

import com.vimensa.ship.admin.APIStart;
import com.vimensa.ship.admin.data.DataProcess;
import com.vimensa.ship.admin.model.ErrorCode;
import com.vimensa.ship.admin.request.AcceptNewClient;
import com.vimensa.ship.admin.request.AcceptNewShipper;
import com.vimensa.ship.admin.request.GetClientCodeReq;
import com.vimensa.ship.admin.request.GetShipperCodeReq;
import com.vimensa.ship.admin.response.CommonResponse;
import com.vimensa.ship.admin.response.GetClientCodeRes;
import com.vimensa.ship.admin.response.GetNewShipperRes;
import com.vimensa.ship.admin.response.GetShipperCodeRes;
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
    /**
     * accept new register-shipper
     * change enable in shipper to 1 and add in user_role
     * @param: shipper_phone
     * */
    @RequestMapping(value = "/acceptShipper", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse acceptShipper(@RequestBody AcceptNewShipper newShipper){
        CommonResponse res = new CommonResponse();
        String shipperPhone = newShipper.getShipper_phone();
        String name = newShipper.getName();
        String mail = newShipper.getMail();
        long timestamp = Calendar.getInstance().getTimeInMillis();
        dao.enableNewRegister(shipperPhone, name, mail, String.valueOf(timestamp));
        dao.addNewShipperInUserRole(shipperPhone);
        res.setError(ErrorCode.SUCCESS);
        logger.info(Controller.class.getName()+" accept new register shipper successfully.");
        return res;
    }
    /**
     * accept new register client
     * change enable in client to 1 and add in user_role
     * @param: client_phone
     * */
    @RequestMapping(value = "/acceptClient",method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse acceptClient(@RequestBody AcceptNewClient client){
        CommonResponse res = new CommonResponse();
        String clientPhone = client.getClient_phone();
        String name = client.getName();
        String mail = client.getMail();
        long timestamp = Calendar.getInstance().getTimeInMillis();
        dao.enableNewRegister(clientPhone, name, mail, String.valueOf(timestamp));
        dao.addNewClientInUserRole(clientPhone);
        res.setError(ErrorCode.SUCCESS);
        logger.info(Controller.class.getName()+" accept new register client successfully.");
        return res;
    }

    /**
     * get shipper code to login
     * @param: shipperPhone
     * */
    @RequestMapping(value = "/getShipperCode",method = RequestMethod.POST)
    @ResponseBody
    public GetShipperCodeRes getShipperCode(@RequestBody GetShipperCodeReq req){
        String shipperPhone = req.getShipper_phone();
        String code = dao.getShipperByPhone(shipperPhone).getCode();
        GetShipperCodeRes res = new GetShipperCodeRes();
        res.setCode(code);
        res.setError(ErrorCode.SUCCESS);
        return res;
    }
    /**
     * get shipper code to login
     * @param: shipperPhone
     * */
    @RequestMapping(value = "/getClientCode",method = RequestMethod.POST)
    @ResponseBody
    public GetClientCodeRes getClientCode(@RequestBody GetClientCodeReq req){
        String clientPhone = req.getClient_code();
        String code = dao.getClientByPhone(clientPhone).getCode();
        GetClientCodeRes res = new GetClientCodeRes();
        res.setCode(code);
        res.setError(ErrorCode.SUCCESS);
        return res;
    }
    /**
     * get all un enable shippers
     *
     * */
    @RequestMapping(value = "/getNewRegister",method = RequestMethod.GET)
    @ResponseBody
    public GetNewShipperRes getNewShipper(){
        GetNewShipperRes res = new GetNewShipperRes();
        res.setShipperLi(dao.getAllUnabledShippers());
        return res;
    }
}
