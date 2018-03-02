package com.vimensa.system.data;

import com.vimensa.system.RunAPI;
import com.vimensa.system.dao.Order;
import com.vimensa.system.dao.Shipper;
import com.vimensa.system.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
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
        String sql = QueryCode.GET_ALL_DRIVERS;
        List<Shipper> shippers = jdbcTemplate.query(sql,
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
}
