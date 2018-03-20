package com.vimensa.ship.shipper.controller;

import com.vimensa.ship.shipper.APIStart;
import com.vimensa.ship.shipper.dao.Shipper;
import com.vimensa.ship.shipper.data.DataProcess;
import com.vimensa.ship.shipper.model.Destination;
import com.vimensa.ship.shipper.model.ErrorCode;
import com.vimensa.ship.shipper.model.Order;
import com.vimensa.ship.shipper.model.Status;
import com.vimensa.ship.shipper.request.OrderID;
import com.vimensa.ship.shipper.request.OrderShipperID;
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
     *
     * @param: phone
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse register(@RequestBody Phone register) {
        CommonResponse res = new CommonResponse();
        String phone = register.getPhone();
        dao.registerNewShipper(phone);
        res.setError(ErrorCode.SUCCESS);
        logger.info(Controller.class.getName() + " REGISTER register new shipper successfully.");
        return res;
    }

    @RequestMapping(value = "/sessionLog", method = RequestMethod.POST)
    public ShipperInfoRes sessionLog(@RequestBody Phone p) {
        String phone = p.getPhone();
        Shipper s = dao.getShipperByPhone(phone);
        dao.shipperLoginLog(s.getShp_id());
        ShipperInfoRes res = new ShipperInfoRes(s.getPhone(), s.getCode(), s.getName(), s.getMail(), s.getShp_id(), s.getStar(), s.getAvatar());
        res.setError(ErrorCode.SUCCESS);
        logger.info(Controller.class.getName() + " SESSION_LOG session log successfully.");
        return res;
    }

    /**
     * get new order from order_shipper on db system
     * call each 45s time.
     */
    @RequestMapping(value = "/getorder", method = RequestMethod.POST)
    @ResponseBody
    public GetOrder getOrder(@RequestBody ShipperID shp) {
        String shp_id = shp.getShp_id();
        int count = dao.countWaitAcceptingOrderShipperSystem(shp_id);
        GetOrder res = new GetOrder();
        if (count <= 0) {
            res.setError(ErrorCode.NO_ORDER);
        } else {
            Order ord = dao.shipperGetDetailOrderByShipperID(shp_id);
            logger.info(Controller.class.getName() + " GET_ORDER get half of detail order successfully.");
            List<Destination> li = dao.getDestinationsByODID(ord.getOd_id());
            logger.info(Controller.class.getName() + " GET_ORDER get all destinations successfully.");
            res = new GetOrder(ord, li);
            res.setError(ErrorCode.SUCCESS);
        }
        return res;
    }

    /**
     * change status in order_shipper to SHIPPER_ACCEPT_ORDER
     * change status in shipper_system to ON_WAY
     * add in order_log with status = WAITING_TAKE_OVER
     * update order set shp_id
     */
    @RequestMapping(value = "/acceptorder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse acceptOrder(@RequestBody OrderShipperID ao) {
        CommonResponse res = new CommonResponse();
        String shp_id = ao.getShp_id();
        String od_id = ao.getOd_id();
        dao.changeStatusInOrderShipperToAccepted(od_id);
        logger.info(Controller.class.getName() + " ACCEPT_ORDER change status in order_shipper to accepted successfully.");
        dao.changeShipperStatusToOnWayInShipperSystem(shp_id);
        logger.info(Controller.class.getName() + " ACCEPT_ORDER change shipper_system status to on_way successfully.");
        dao.addNewOrderLogWaitTakeOver(od_id);
        logger.info(Controller.class.getName() + " ACCEPT_ORDER add new order_log wait_to_take_over successfully.");
        dao.setShipperIDInOrder(od_id, shp_id);
        logger.info(Controller.class.getName() + " ACCEPT_ORDER set shp-id in order successfully.");
        res.setError(ErrorCode.SUCCESS);
        return res;
    }

    /**
     * change status in order_system to urgent_order
     * delete in order_shipper
     */
    @RequestMapping(value = "/rejectorder", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse rejectOrder(@RequestBody OrderShipperID id) {
        CommonResponse res = new CommonResponse();
        String shp_id = id.getShp_id();
        String od_id = id.getOd_id();
        dao.changeStatusToUrgentOrderSystem(od_id);
        logger.info(Controller.class.getName() + " REJECT_ORDER change status to urgent in order_system successfully.");
        dao.deleteOrderShipperByShipperID(shp_id);
        logger.info(Controller.class.getName() + " REJECT_ORDER delete in order_shipper successfully.");
        res.setError(ErrorCode.SUCCESS);
        return res;
    }

    /**
     * insert into order_log
     */
    @RequestMapping(value = "/reachedshop", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse reachedShop(@RequestBody OrderID oi) {
        CommonResponse res = new CommonResponse();
        String od_id = oi.getOd_id();
        dao.addOrderLogDelivering(od_id);
        logger.info(Controller.class.getName() + " REACHED_SHOP insert into order_log status-delivering successfully.");
        res.setError(ErrorCode.SUCCESS);
        return res;
    }

    /**
     * insert into order_log - status delivered
     * delete order_shipper
     * delete order_system
     * update order - status-success
     * change status in shipper_system
     */
    @RequestMapping(value = "/deliveredsuccessfully", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse deliveredSuccessfully(@RequestBody OrderShipperID id) {
        CommonResponse res = new CommonResponse();
        String shp_id = id.getShp_id();
        String od_id = id.getOd_id();
        dao.addOrderLogDelivered(od_id);
        logger.info(Controller.class.getName() + " DELIVERED_SUCCESSFULLY insert into order_log status-delivered successfully.");
        dao.deleteOrderShipeprByOrderID(od_id);
        logger.info(Controller.class.getName() + " DELIVERED_SUCCESSFULLY delete order_shipper successfully.");
        dao.deleteOrderSystemByOrderID(od_id);
        logger.info(Controller.class.getName() + " DELIVERED_SUCCESSFULLY delete order_system successfully.");
        dao.updateSuccessfulStatusOrder(od_id);
        logger.info(Controller.class.getName() + " DELIVERED_SUCCESSFULLY update successful status in order successfully.");
        dao.changeShipperStatusToAwakeInShipperSystem(shp_id);
        logger.info(Controller.class.getName() + " DELIVERED_SUCCESSFULLY change status in shipper_system to awake successfully.");
        res.setError(ErrorCode.SUCCESS);
        return res;
    }
    /**
     * change status in shipper_system to awake
     * add in order_log
     * */
    @RequestMapping(value = "/deliveredUnsuccessfully",method = RequestMethod.POST)
    @ResponseBody
    public void deliveredUnsuccessfully(@RequestBody OrderShipperID id){
        String od_id = id.getOd_id();
        String shp_id = id.getShp_id();

    }
}
