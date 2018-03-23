package com.vimensa.ship.client.controller;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.dao.Client;
import com.vimensa.ship.client.data.DataProcess;
import com.vimensa.ship.client.model.ErrorCode;
import com.vimensa.ship.client.request.*;
import com.vimensa.ship.client.response.BaseResponse;
import com.vimensa.ship.client.response.ClientInfoRes;
import com.vimensa.ship.client.response.CommonResponse;
import com.vimensa.ship.client.response.GetShipperRes;
import com.vimensa.ship.client.service.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
     *
     * @param: phone
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse register(@RequestBody Phone register) {
        BaseResponse res;
        try {
            String phone = register.getPhone();
            dao.registerClient(phone);
            logger.info(Controller.class.getName() + " REGISTER insert into client successfully");
            res = new CommonResponse(ErrorCode.SUCCESS);

        } catch (Exception e) {
            res = new CommonResponse(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " REGISTER exception" + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/sessionLog", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse sessionLog(@RequestBody Phone p) {
        BaseResponse res ;
        try {
            String phone = p.getPhone();
            Client cli = dao.getClientByPhone(phone);
            dao.clientLoginLog(cli.getCli_id());
            logger.info(Controller.class.getName() + " SESSION_LOG client-login-log successfully.");
            res = new ClientInfoRes(phone, cli.getCode(), cli.getName(), cli.getMail(), cli.getPlace(), cli.getCli_id(), cli.getAvatar());
            res.setError(ErrorCode.SUCCESS);
        } catch (Exception e) {
            res = new CommonResponse(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " SESSION_LOG exception" + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    /**
     * urgent order
     * insert into order with status = unsuccessful
     * insert into destinations
     * insert into order_system with status = urgent_order
     *
     * @param: urgent-order-info
     */
    @RequestMapping(value = "/urgentorder", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse urgentOrder(@RequestBody UrgentOrderRequest ord) {
        BaseResponse res ;
        try {
            String od_id = Calendar.getInstance().getTimeInMillis() + "OD";
            dao.addNewUrgentOrder(ord, od_id);
            logger.info(Controller.class.getName() + " URGENT_ORDER add new urgent-order successfully.");
            dao.addDestinations(ord.getToLi(), od_id);
            logger.info(Controller.class.getName() + " URGENT_ORDER add destinations successfully.");
            dao.addUrgentOrderSystem(od_id);
            logger.info(Controller.class.getName() + " URGENT_ORDER add urgent-order in order-system successfully.");
            res = new CommonResponse(ErrorCode.SUCCESS);
        } catch (Exception e) {
            res = new CommonResponse(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " URGENT_ORDER exception" + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    /**
     * wait order
     * insert into order with status = unsuccessful
     * insert into destinations
     * insert into order_system with status = wait_order
     *
     * @param: wait-order-info
     */
    @RequestMapping(value = "/waitorder", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse waitOrder(@RequestBody WaitOrderRequest ord) {
        BaseResponse res;
        try {
            String od_id = Calendar.getInstance().getTimeInMillis() + "OD";
            dao.addNewWaitOrder(ord, od_id);
            logger.info(Controller.class.getName() + " WAIT_ORDER add new wait-order successfully.");
            dao.addDestinations(ord.getToLi(), od_id);
            logger.info(Controller.class.getName() + " WAIT_ORDER add destinations successfully.");
            dao.addWaitOrderSystem(od_id);
            logger.info(Controller.class.getName() + " WAIT_ORDER add wait-order in order-system successfully.");
            res = new CommonResponse(ErrorCode.SUCCESS);
        } catch (Exception e) {
            res = new CommonResponse(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " WAIT_ORDER exception" + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    /**
     * getshipper is called right after /urgentorder
     * get shipper-accepted-order in order_shipper
     */
    @RequestMapping(value = "/getshipper", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse getShipper(@RequestBody OrderID id) {
        BaseResponse res;
        try {
            String od_id = id.getOd_id();
            try {
                res = dao.getShipperAceptedOrder(od_id);
                logger.info(Controller.class.getName() + " GET_SHIPPER get shipper-accepted-order successfully.");
                res.setError(ErrorCode.SUCCESS);
            } catch (EmptyResultDataAccessException e) {
                logger.info(Controller.class.getName() + " GET_SHIPPER found no shipper yet.");
                res = new CommonResponse(ErrorCode.FOUND_NO_SHIPPER_YET);
            }
        } catch (Exception e) {
            res = new CommonResponse(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " GET_SHIPPER exception" + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }
    @RequestMapping(value = "/checkorderstatus",method = RequestMethod.POST)
    @ResponseBody
    public void checkOrderStatus(@RequestBody OrderID id){

    }

    /**
     * delete in order_system
     * delete in order_shipper
     * change shipper-status in shipper_system
     * add in order_log
     */
    @RequestMapping(value = "/cancelorder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse cancelOrder(@RequestBody OrderID id) {
        CommonResponse res = new CommonResponse();
        try {
            String od_id = id.getOd_id();
            dao.deleteInOrderSystem(od_id);
            logger.info(Controller.class.getName() + " CANCEL_ORDER delete in order_system successfully.");
            dao.deleteInOrderShipper(od_id);
            logger.info(Controller.class.getName() + " CANCEL_ORDER delete in order_shipper successfully.");

        } catch (Exception e) {
            res.setError(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " CANCEL_ORDER exception" + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }
}
