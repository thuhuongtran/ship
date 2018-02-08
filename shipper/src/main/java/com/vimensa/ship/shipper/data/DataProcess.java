package com.vimensa.ship.shipper.data;

import com.vimensa.ship.shipper.APIStart;
import com.vimensa.ship.shipper.response.GetOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Transactional
@Repository
public class DataProcess {
    private final static Logger logger = LoggerFactory.getLogger(APIStart.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    public DataProcess(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Integer countOrderShipperSystem(String shipper_phone){
        String sql = QueryCode.COUNT_ORDER_SHIPPER_SYSTEM_BY_PHONE;
        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{shipper_phone}, Integer.class);
        return count;
    }
    public GetOrder getDetailOrderSystem(String order_id){
        String sql = QueryCode.GET_DETAIL_ORDER_SYSTEM;
        GetOrder ord = jdbcTemplate.queryForObject(sql, new Object[]{order_id},
                new BeanPropertyRowMapper<>(GetOrder.class));
        return ord;
    }

}