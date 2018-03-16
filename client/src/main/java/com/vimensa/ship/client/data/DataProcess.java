package com.vimensa.ship.client.data;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.dao.Client;
import com.vimensa.ship.client.dao.Shipper;
import com.vimensa.ship.client.model.Destination;
import com.vimensa.ship.client.model.Status;
import com.vimensa.ship.client.request.UrgentOrderRequest;
import com.vimensa.ship.client.request.WaitOrderRequest;
import com.vimensa.ship.client.service.LoginCode;
import com.vimensa.ship.client.service.Tasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
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

    public void addNewUrgentOrder(UrgentOrderRequest ord, String od_id) {
        long timestamp = Calendar.getInstance().getTimeInMillis();
        double fee = Tasks.getFee(ord.getDistance());
        String sql = QueryCode.ADD_NEW_ORDER;
        jdbcTemplate.update(sql,new Object[]{od_id,timestamp,ord.getCli_id(),ord.getAdv_paym(),ord.getItem_type(),ord.getNote()
                ,ord.getFrom(),ord.getFrom_lat(),ord.getFrom_log(),ord.getCustm_phone(),ord.getDistance(),fee,timestamp,Status.UNSUCCESSFUL
         });
    }

    public void addNewWaitOrder(WaitOrderRequest ord,String od_id) {
        long timestamp = Calendar.getInstance().getTimeInMillis();
        double fee = Tasks.getFee(ord.getDistance());
        String sql = QueryCode.ADD_NEW_ORDER;
        jdbcTemplate.update(sql,new Object[]{od_id,timestamp,ord.getCli_id(),ord.getAdv_paym(),ord.getItem_type(),ord.getNote()
                ,ord.getFrom(),ord.getFrom_lat(),ord.getFrom_log(),ord.getCustm_phone(),ord.getDistance(),fee,ord.getWait_time(),Status.UNSUCCESSFUL
        });
    }
    public void addUrgentOrderSystem(String od_id){
        long timestmp = Calendar.getInstance().getTimeInMillis();
        String sql = QueryCode.ADD_NEW_ORDER_SYSTEM;
        jdbcTemplate.update(sql, new Object[]{od_id, timestmp, Status.URGENT_ORDER});
    }
    public void addWaitOrderSystem(String od_id){
        long timestmp = Calendar.getInstance().getTimeInMillis();
        String sql = QueryCode.ADD_NEW_ORDER_SYSTEM;
        jdbcTemplate.update(sql, new Object[]{od_id, timestmp, Status.WAIT_ORDER});
    }
    public void addDestinations(List<Destination> dli, String od_id){
        String sql = QueryCode.ADD_NEW_DESTINATION;
        jdbcTemplate.batchUpdate(sql,new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i)
                    throws SQLException {

                Destination d = dli.get(i);
                ps.setString(1, od_id);
                ps.setDouble(2, d.getTo_lat());
                ps.setDouble(3, d.getTo_log());
                ps.setString(4,d.getTo());

            }

            @Override
            public int getBatchSize() {
                return dli.size();
            }
        });

    }

}

