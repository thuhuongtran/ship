package com.vimensa.ship.client.data;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.dao.Client;
import com.vimensa.ship.client.dao.Shipper;
import com.vimensa.ship.client.model.Status;
import com.vimensa.ship.client.request.UrgentOrderRequest;
import com.vimensa.ship.client.request.WaitOrderRequest;
import com.vimensa.ship.client.service.LoginCode;
import com.vimensa.ship.client.service.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.Calendar;

@Transactional
@Repository
public class DataProcess {
    private final static Logger logger = LoggerFactory.getLogger(APIStart.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public DataProcess(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void registerClient(String phone) {
        String sql = QueryCode.REGISTER_CLIENT;
        String cli_id = Calendar.getInstance().getTimeInMillis()+"cli";
        jdbcTemplate.update(sql, new Object[]{phone, LoginCode.getCode(),Status.CLIENT_UNOFFICIAL,cli_id});
    }
    public void clientLoginLog(String user_id){
        String sql = QueryCode.LOGIN_LOG;
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String time_in = Tasks.formatDate(new java.util.Date());
        jdbcTemplate.update(sql, new Object[]{user_id, time_in, timestamp, Status.CLIENT_ROLE});
    }

    public Client getClientByPhone(String phone) {
        String sql = QueryCode.GET_CLIENT_BY_PHONE;
        Client client = jdbcTemplate.queryForObject(sql, new Object[]{phone}, new BeanPropertyRowMapper<>(Client.class));
        return client;
    }

    public Shipper getShipperByPhone(String phone) {
        String sql = QueryCode.GET_SHIPPER_BY_PHONE;
        Shipper shipper = jdbcTemplate.queryForObject(sql, new Object[]{phone}, new BeanPropertyRowMapper<>(Shipper.class));
        return shipper;
    }

    public void addNewUrgentOrderSystem(UrgentOrderRequest ord) {
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String orderID = timestamp+"OD";
        double fee = Tasks.getFee(ord.getDistance());
        String sql = QueryCode.ADD_NEW_ORDER_SYSTEM;
        jdbcTemplate.update(sql,new Object[]{
            orderID, ord.getFrom_lat(),ord.getFrom_log(), ord.getTo_lat(), ord.getTo_log(), timestamp, Status.URGENT_ORDER,
                    ord.getClient_phone(),ord.getAdv_paym(), ord.getMass(), ord.getNote(),
                ord.getFrom(), ord.getTo(), ord.getDistance(), fee, ord.getItem_type(),timestamp});
    }

    public void addNewWaitOrderSystem(WaitOrderRequest ord) {
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String orderID = timestamp+"OD";
        double fee = Tasks.getFee(ord.getDistance());
        String sql = QueryCode.ADD_NEW_ORDER_SYSTEM;
        jdbcTemplate.update(sql,new Object[]{
                orderID, ord.getFrom_lat(),ord.getFrom_log(), ord.getTo_lat(), ord.getTo_log(), timestamp, Status.WAIT_ORDER,
                ord.getClient_phone(),ord.getAdv_paym(), ord.getMass(), ord.getNote(),
                ord.getFrom(), ord.getTo(), ord.getDistance(), fee, ord.getItem_type(),ord.getWait_time()});
    }

}

