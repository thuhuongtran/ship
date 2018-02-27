package com.vimensa.ship.shipper.data;

import com.vimensa.ship.shipper.APIStart;
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
    public void registerNewShipper(String phone){
        String sql = QueryCode.REGISTER_SHIPPER;
        jdbcTemplate.update(sql, new Object[]{phone, LoginCode.getCode(),Status.UNENABLED_SHIPPER});
    }
    public void shipperLoginLog(String phone){
        String sql = QueryCode.LOGIN_LOG;
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String time_in = Tasks.formatDate(new Date());
        jdbcTemplate.update(sql, new Object[]{phone, time_in, timestamp, Status.SHIPPER_ROLE});
    }

}
