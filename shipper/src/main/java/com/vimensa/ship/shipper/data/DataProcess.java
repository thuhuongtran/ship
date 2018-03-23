package com.vimensa.ship.shipper.data;

import com.vimensa.ship.shipper.APIStart;
import com.vimensa.ship.shipper.dao.Shipper;
import com.vimensa.ship.shipper.model.Destination;
import com.vimensa.ship.shipper.model.Order;
import com.vimensa.ship.shipper.model.Status;
import com.vimensa.ship.shipper.service.LoginCode;
import com.vimensa.ship.shipper.service.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class DataProcess {
    private final static Logger logger = LoggerFactory.getLogger(APIStart.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public DataProcess(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    public void registerNewShipper(String phone){
        String sql = QueryCode.REGISTER_SHIPPER;
        String shp_id = Calendar.getInstance().getTimeInMillis() + "shp";
        jdbcTemplate.update(sql, new Object[]{phone, LoginCode.getCode(),Status.UNENABLED_SHIPPER,shp_id});
    }
    public Shipper getShipperByPhone(String phone){
        String sql = QueryCode.GET_SHIPPER_BY_PHONE;
        Shipper s = jdbcTemplate.queryForObject(sql, new Object[]{phone},
                new BeanPropertyRowMapper<>(Shipper.class));
        return s;
    }
    public void shipperLoginLog(String phone){
        String sql = QueryCode.LOGIN_LOG;
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String time_in = Tasks.formatDate(new Date());
        jdbcTemplate.update(sql, new Object[]{phone, time_in, timestamp, Status.SHIPPER_ROLE});
    }
    public Integer countWaitAcceptingOrderShipperSystem(String shp_id){
        String sql = QueryCode.COUNT_WAIT_ACCEPTING_ORDER_SHIPPER_SYSTEM_BY_SHIPPERID;
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{shp_id, Status.WAIT_SHIPPER_DECISION}, Integer.class);
        return count;
    }
    public Order shipperGetDetailOrderByShipperID(String shp_id){
        String sql = QueryCode.SHIPPER_GET_HALF_DETAIL_ORDER;
        Order ord = jdbcTemplate.queryForObject(sql, new Object[]{shp_id, Status.WAIT_SHIPPER_DECISION},
                new BeanPropertyRowMapper<>(Order.class));
        return ord;
    }
    public List<Destination> getDestinationsByODID(String od_id){
        String sql = QueryCode.GET_DESTINATIONS_BY_OD_ID;
        List<Destination> li = jdbcTemplate.query(sql, new Object[]{od_id},
                new BeanPropertyRowMapper<>(Destination.class));
        return li;
    }
    public void changeStatusInOrderShipperToAccepted(String od_id){
        String sql = QueryCode.CHANGE_STATUS_TO_ACCEPTED_IN_ORDER_SHIPPER;
        jdbcTemplate.update(sql, new Object[]{Status.SHIPPER_ACCEPT_ORDER,od_id,Status.WAIT_SHIPPER_DECISION});
    }
    public void changeShipperStatusToOnWayInShipperSystem(String shp_id){
        String sql = QueryCode.CHANGE_SHIPPER_STATUS_SHIPPER_SYSTEM;
        jdbcTemplate.update(sql, new Object[]{Status.SHIPPER_ON_WAY, shp_id,Status.WAIT_SHIPPER_DECISION});
    }
    public void addNewOrderLogWaitTakeOver(String od_id) {
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String sql = QueryCode.ADD_NEW_ORDER_LOG;
        jdbcTemplate.update(sql, new Object[]{od_id,timestamp,Status.ORDER_WAITING_TAKE_OVER});
    }
    public void changeStatusToUrgentOrderSystem(String od_id){
        String sql = QueryCode.CHANGE_STATUS_TO_URGENT_ORDER_SYSTEM;
        jdbcTemplate.update(sql, new Object[]{Status.URGENT_ORDER, od_id});
    }
    public void deleteOrderShipperByShipperID(String shp_id){
        String sql = QueryCode.DELETE_ORDER_SHIPPER_BY_SHIPPER_ID;
        jdbcTemplate.update(sql, new Object[]{shp_id,Status.WAIT_SHIPPER_DECISION});
    }
    public void addOrderLogDelivering(String od_id){
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String sql = QueryCode.ADD_NEW_ORDER_LOG;
        jdbcTemplate.update(sql, new Object[]{od_id, timestamp, Status.ORDER_DELIVERING});
    }
    public void setShipperIDInOrder(String od_id,String shp_id){
        String sql = QueryCode.SET_SHIPPER_ID_IN_ORDER;
        jdbcTemplate.update(sql, new Object[]{shp_id, od_id, Status.UNSUCCESSFUL});
    }
    public void addOrderLogDelivered(String od_id){
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String sql = QueryCode.ADD_NEW_ORDER_LOG;
        jdbcTemplate.update(sql, new Object[]{od_id, timestamp, Status.ORDER_DELIVERED});
    }
    public void deleteOrderShipeprByOrderID(String od_id){
        String sql = QueryCode.DELETE_ORDER_SHIPPER_BY_ORDER_ID;
        jdbcTemplate.update(sql, new Object[]{od_id, Status.SHIPPER_ACCEPT_ORDER});
    }
    public void deleteOrderSystemByOrderID(String od_id){
        String sql = QueryCode.DELETE_ORDER_SYSTEM_BY_ORDER_ID;
        jdbcTemplate.update(sql, new Object[]{od_id, Status.WAIT_SHIPPER_DECISION});
    }
    public void updateSuccessfulStatusOrder(String od_id){
        String sql = QueryCode.UPDATE_SUCCESSFUL_STATUS_ORDER;
        jdbcTemplate.update(sql, new Object[]{Status.SUCCESSFUL, od_id, Status.UNSUCCESSFUL});
    }
    public void changeShipperStatusToAwakeInShipperSystem(String shp_id){
        String sql = QueryCode.CHANGE_SHIPPER_STATUS_SHIPPER_SYSTEM;
        jdbcTemplate.update(sql, new Object[]{Status.SHIPPER_AWAKE, shp_id,Status.SHIPPER_ON_WAY});
    }
    public void addOrderLogUnsuccessfulDeliverd(String od_id){
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String sql = QueryCode.ADD_NEW_ORDER_LOG;
        jdbcTemplate.update(sql, new Object[]{od_id, timestamp, Status.ORDER_DELIVERD_UNSUCCESSFULLY});
    }
}
