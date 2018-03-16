package com.vimensa.system.data;

import com.vimensa.system.RunAPI;
import com.vimensa.system.dao.Shipper;
import com.vimensa.system.model.Order;
import com.vimensa.system.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Calendar;
import java.util.List;

@Transactional
@Repository
public class DataProcess {
    private final static Logger logger = LoggerFactory.getLogger(RunAPI.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public DataProcess(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * get drivers from shipper_system
     */
    public List<Shipper> getAllAwakeShippers() {
        String sql = QueryCode.GET_ALL_AWAKE_SHIPPERS;
        List<Shipper> shippers = jdbcTemplate.query(sql, new Object[]{Status.SHIPPER_AWAKE},
                new BeanPropertyRowMapper<>(Shipper.class));
        return shippers;
    }

    public Shipper getShipperByPhone(String phone) {
        String sql = QueryCode.GET_SHIPPER_BY_PHONE;
        Shipper shipper = jdbcTemplate.queryForObject(sql, new Object[]{phone}, new BeanPropertyRowMapper<>(Shipper.class));
        return shipper;
    }

    public void addNewOrderShipperSystem(String shp_id, String od_id) {
        String sql = QueryCode.ADD_NEW_ORDER_SHIPPER_SYSTEM;
        long timestamp = Calendar.getInstance().getTimeInMillis();
        jdbcTemplate.update(sql,new Object[]{od_id, shp_id, Status.WAIT_SHIPPER_DECISION,timestamp});
    }

    public Order getEarliestDeliveryNeededUrgentOrder() {
        String sql = QueryCode.GET_EARLIEST_DELIVERY_NEEDED_URGENT_ORDER;
        return jdbcTemplate.queryForObject(sql, new Object[]{Status.URGENT_ORDER}, new BeanPropertyRowMapper<>(Order.class));

    }

    public void changeStatusToWaitShipperDecisionOrderSystem(String od_id) {
        String sql = QueryCode.CHANGE_STATUS_TO_WAIT_SHIPPER_DECISION_ORDER_SYSTEM;
        jdbcTemplate.update(sql, new Object[]{Status.WAIT_SHIPPER_DECISION, od_id});
    }

    public void changeWaitOrderStatusToUrgentOrderSystem() {
        String sql = QueryCode.CHANGE_WAIT_ORDER_STATUS_TO_URGENT_ORDER_SYSTEM;
        jdbcTemplate.update(sql, new Object[]{Status.URGENT_ORDER, Status.WAIT_ORDER});
    }
}
