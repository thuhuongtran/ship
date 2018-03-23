package com.vimensa.ship.admin.controller;

import com.vimensa.ship.admin.APIStart;
import com.vimensa.ship.admin.dao.Admin;
import com.vimensa.ship.admin.data.DataProcess;
import com.vimensa.ship.admin.model.ErrorCode;
import com.vimensa.ship.admin.request.*;
import com.vimensa.ship.admin.response.*;
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
     * accept new register-shipper
     * change enable in shipper to 1 and add in user_role
     *
     * @param: shipper_phone
     */
    @RequestMapping(value = "/acceptShipper", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse acceptShipper(@RequestBody AcceptNewShipper newShipper) {
        BaseResponse res;
        try {
            String shipperPhone = newShipper.getShipper_phone();
            String name = newShipper.getName();
            String mail = newShipper.getMail();
            dao.enableNewRegisterShipper(shipperPhone, name, mail);
            logger.info("ACCEPT_SHIPPER enable new register shipper successfully.");
            dao.addNewShipperInUserRole(dao.getShipperIDByPhone(shipperPhone), shipperPhone);
            logger.info("ACCEPT_SHIPPER add in user-role successfully.");
            res = new CommonResponse(ErrorCode.SUCCESS);
        } catch (Exception e) {
            res = new CommonResponse(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " ACCEPT_SHIPPER exception: " + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    /**
     * accept new register client
     * change enable in client to 1 and add in user_role
     *
     * @param: client_phone
     */
    @RequestMapping(value = "/acceptClient", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse acceptClient(@RequestBody AcceptNewClient client) {
        BaseResponse res;
        String clientPhone = client.getClient_phone();
        try {

            String name = client.getName();
            String mail = client.getMail();
            dao.enableNewRegisterClient(clientPhone, name, mail);
            logger.info("ACCEPT_CLIENT enable new register client successfully.");
            dao.addNewClientInUserRole(dao.getClientIDByPhone(clientPhone), clientPhone);
            logger.info("ACCEPT_CLIENT add in user_role successfully.");
            res = new CommonResponse(ErrorCode.SUCCESS);

        } catch (Exception e) {
            res = new CommonResponse(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " ACCEPT_CLIENT exception: " + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    /**
     * get shipper code to login
     *
     * @param: shipperPhone
     */
    @RequestMapping(value = "/getShipperCode", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse getShipperCode(@RequestBody GetShipperCodeReq req) {
        BaseResponse res;
        String shipperPhone = req.getShipper_phone();
        try {
            String code = dao.getShipperByPhone(shipperPhone).getCode();
            logger.info("GET_SHIPPER_CODE get shipper by phone successfully.");
            res = new GetShipperCodeRes(code, ErrorCode.SUCCESS);

        } catch (Exception e) {
            res = new CommonResponse(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " GET_SHIPPER_CODE exception: " + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    /**
     * get shipper code to login
     *
     * @param: shipperPhone
     */
    @RequestMapping(value = "/getClientCode", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse getClientCode(@RequestBody GetClientCodeReq req) {
        BaseResponse res;
        String clientPhone = req.getClient_code();
        try{
            String code = dao.getClientByPhone(clientPhone).getCode();
            logger.info("GET_CLIENT_CODE get client by phone successfully.");
            res = new GetClientCodeRes(code,ErrorCode.SUCCESS);
        }
        catch (Exception e) {
            res = new CommonResponse(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " GET_CLIENT_CODE exception: " + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    /**
     * get all un enable shippers
     */
    @RequestMapping(value = "/getNewRegister", method = RequestMethod.GET)
    @ResponseBody
    public BaseResponse getNewShipper() {
        BaseResponse res;
        try {
            List<String> shli = dao.getAllUnabledShippers();
            logger.info("GET_NEW_REGISTER get all unable shipper successfully.");
            List<String> clili = dao.getAllUnabledClients();
            logger.info("GET_NEW_REGISTER get all unable client successfully.");
            res = new GetNewShipperRes(shli,clili,ErrorCode.SUCCESS);
        }
        catch (Exception e) {
            res = new CommonResponse(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " GET_NEW_REGISTER exception: " + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/sessionLog", method = RequestMethod.POST)
    public BaseResponse sessionLog(@RequestBody Phone p) {
        BaseResponse res;
        String phone = p.getPhone();
        try{
            Admin a = dao.getAdminByPhone(phone);
            logger.info("SESSION_LOG get admin by phone successfully.");
            dao.adminLoginLog(a.getAdm_id());
            logger.info("SESSION_LOG login-log in user-log successfully.");
            res = new AdminInfo(a.getAdm_id(), a.getPhone(), a.getCode(), a.getName(), a.getMail(), a.getAvatar());
            res.setError(ErrorCode.SUCCESS);
        }
        catch (Exception e) {
            res = new CommonResponse(ErrorCode.SYSTEM_EXCEPTION);
            logger.info(Controller.class.getName() + " SESSION_LOG exception: " + e.getMessage());
            e.printStackTrace();
        }
        return res;
    }
}
