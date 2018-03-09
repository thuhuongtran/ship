package com.vimensa.ship.shipper.data;

import com.vimensa.ship.shipper.APIStart;
import com.vimensa.ship.shipper.dao.OrderSystem;
import com.vimensa.ship.shipper.dao.Shipper;
import com.vimensa.ship.shipper.model.Status;
import com.vimensa.ship.shipper.response.GetOrder;
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

@Transactional
@Repository
public class DataProcess {
    private final static Logger logger = LoggerFactory.getLogger(APIStart.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public DataProcess(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Integer countWaitAcceptingOrderShipperSystem(String shipper_phone){
        String sql = QueryCode.COUNT_WAIT_ACCEPTING_ORDER_SHIPPER_SYSTEM_BY_PHONE;
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{shipper_phone, Status.WAIT_SHIPPER_DECISION}, Integer.class);
        return count;
    }
    public GetOrder getDetailOrderSystem(String order_id){
        String sql = QueryCode.GET_DETAIL_ORDER_SYSTEM;
        GetOrder ord = jdbcTemplate.queryForObject(sql, new Object[]{order_id, Status.WAIT_SHIPPER_DECISION},
                new BeanPropertyRowMapper<>(GetOrder.class));
        return ord;
    }
    public void registerNewShipper(String phone){
        String sql = QueryCode.REGISTER_SHIPPER;
        String shp_id = Calendar.getInstance().getTimeInMillis() + "shp";
        jdbcTemplate.update(sql, new Object[]{phone, LoginCode.getCode(),Status.UNENABLED_SHIPPER,shp_id});
    }
    public void shipperLoginLog(String phone){
        String sql = QueryCode.LOGIN_LOG;
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String time_in = Tasks.formatDate(new Date());
        jdbcTemplate.update(sql, new Object[]{phone, time_in, timestamp, Status.SHIPPER_ROLE});
    }
    public void changeStatusInOrderShipperToAccepted(String order_id){
        String sql = QueryCode.CHANGE_STATUS_TO_ACCEPTED_IN_ORDER_SHIPPER;
        jdbcTemplate.update(sql, new Object[]{Status.SHIPPER_ACCEPT_ORDER,order_id,Status.WAIT_SHIPPER_DECISION});
    }
    public void changeShipperStatusToOnWayInShipperSystem(String phone){
        String sql = QueryCode.CHANGE_SHIPPER_STATUS_TO_ON_WAY_SHIPPER_SYSTEM;
        jdbcTemplate.update(sql, new Object[]{Status.SHIPPER_ON_WAY, phone});
    }
    public OrderSystem getOrderSystemByOrderID(String order_id){
        String sql = QueryCode.GET_ORDER_SYSTEM_BY_ORDERID;
        OrderSystem o = jdbcTemplate.queryForObject(sql, new Object[]{order_id},
                new BeanPropertyRowMapper<>(OrderSystem.class));
        return o;
    }
    public void addNewOrderLog(OrderSystem o, String shipper_phone){
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String sql =    QueryCode.ADD_NEW_ORDER_LOG;
        jdbcTemplate.update(sql, new Object[]{o.getOrder_id(), timestamp, Status.ORDER_WAITING_TAKE_OVER, o.getClient_phone(),
                shipper_phone, o.getAdv_paym(), o.getMass(), o.getNote(), o.getFrom(), o.getTo(), o.getDistance(), o.getFee(), o.getItem_type(),
                o.getTimestamp(), o.getWait_time()});
    }
    public Shipper getShipperByPhone(String phone){
        String sql = QueryCode.GET_SHIPPER_BY_PHONE;
        Shipper s = jdbcTemplate.queryForObject(sql, new Object[]{phone},
                new BeanPropertyRowMapper<>(Shipper.class));
        return s;
    }
}
