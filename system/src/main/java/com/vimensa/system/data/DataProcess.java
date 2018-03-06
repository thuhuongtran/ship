package com.vimensa.system.data;

import com.vimensa.system.RunAPI;
import com.vimensa.system.dao.Order;
import com.vimensa.system.dao.Shipper;
import com.vimensa.system.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
     * */
    public List<Shipper> findAllDrivers(){
        String sql = QueryCode.GET_ALL_AWAKE_DRIVERS;
        List<Shipper> shippers = jdbcTemplate.query(sql,new Object[]{Status.SHIPPER_AWAKE},
                new BeanPropertyRowMapper<>(Shipper.class));
        return shippers;
    }
    public Shipper getShipperByPhone(String phone){
        String sql = QueryCode.GET_SHIPPER_BY_PHONE;
        Shipper shipper = jdbcTemplate.queryForObject(sql, new Object[]{phone}, new BeanPropertyRowMapper<>(Shipper.class));
        return shipper;
    }
    public void addNewOrderShipperSystem(String shipperPhone, String orderID){
        String sql = QueryCode.ADD_NEW_ORDER_SHIPPER_SYSTEM;
        long timestamp = Calendar.getInstance().getTimeInMillis();
        jdbcTemplate.update(sql, timestamp,orderID,shipperPhone, Status.WAIT_SHIPPER_DECISION);
    }
    public List<Order> getAllUrgentOrders(){
        String sql = QueryCode.GET_ALL_ORDER_SYSTEM;
        List<Order> ords =jdbcTemplate.query(sql,new Object[]{Status.URGENT_ORDER},new RowMapper<Order>(){
            @Override
            public Order mapRow(ResultSet rs, int rownumber) throws SQLException {
               Order o = new Order();
               o.setOrder_id(rs.getString(1));
               o.setFrom_lat(rs.getDouble(2));
               o.setFrom_log(rs.getDouble(3));
               o.setTo_lat(rs.getDouble(4));
               o.setTo_log(rs.getDouble(5));
               o.setWait_time(rs.getLong(6));
               return o;
            }
        });
        return ords;
    }
    public Order getEarliestDeliveryNeededUrgentOrder(){
        String sql = QueryCode.GET_EARLIEST_DELIVERY_NEEDED_URGENT_ORDER;
        return jdbcTemplate.query(sql,new Object[]{Status.URGENT_ORDER}, new ResultSetExtractor<Order>() {
            @Override
            public Order extractData(ResultSet rs) throws SQLException, DataAccessException {

                if (rs.next()) {
                    Order o = new Order();
                    o.setOrder_id(rs.getString(1));
                    o.setFrom_lat(rs.getDouble(2));
                    o.setFrom_log(rs.getDouble(3));
                    o.setTo_lat(rs.getDouble(4));
                    o.setTo_log(rs.getDouble(5));
                    o.setWait_time(rs.getLong(6));
                    return o;
                }
                return null;
            }
        });

    }
    public void deleteHandledOrderSystem(String order_id){
        String sql = QueryCode.DELETE_HANDLED_ORDER_SYSTEM;
        jdbcTemplate.update(sql, new Object[]{order_id});
    }
    public void changeStatusToWaitShipperDecisionOrderSystem(String order_id){
        String sql = QueryCode.CHANGE_STATUS_TO_WAIT_SHIPPER_DECISION_ORDER_SYSTEM;
        jdbcTemplate.update(sql, new Object[]{Status.WAIT_SHIPPER_DECISION, order_id});
    }
    public void changeWaitOrderStatusToUrgentOrderSystem(){
        String sql = QueryCode.CHANGE_WAIT_ORDER_STATUS_TO_URGENT_ORDER_SYSTEM;
        jdbcTemplate.update(sql, new Object[]{Status.URGENT_ORDER, Status.WAIT_ORDER});
    }
}
