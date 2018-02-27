package com.vimensa.ship.admin.data;

import com.vimensa.ship.admin.APIStart;
import com.vimensa.ship.admin.dao.Client;
import com.vimensa.ship.admin.dao.Shipper;
import com.vimensa.ship.admin.model.Status;
import com.vimensa.ship.admin.service.Tasks;
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

    public void enableNewRegisterShipper(String phone, String name, String mail, String timestamp) {
        String sql = QueryCode.ACCEPT_NEW_REGISTER_SHIPPER_ENABLE;
        jdbcTemplate.update(sql, new Object[]{name, mail, Status.ENABLED_USER, timestamp, phone});
    }
    public void enableNewRegisterClient(String phone, String name, String mail, String timestamp) {
        String sql = QueryCode.ACCEPT_NEW_REGISTER_CLIENT_ENABLE;
        jdbcTemplate.update(sql, new Object[]{name, mail, Status.ENABLED_USER, timestamp, phone});
    }

    public Shipper getShipperByPhone(String phone) {
        String sql = QueryCode.GET_SHIPPER_BY_PHONE;
        Shipper shipper = jdbcTemplate.queryForObject(sql, new Object[]{phone},
                new BeanPropertyRowMapper<>(Shipper.class));
        return shipper;
    }

    public Client getClientByPhone(String phone) {
        String sql = QueryCode.GET_CLIENT_BY_PHONE;
        Client client = jdbcTemplate.queryForObject(sql, new Object[]{phone},
                new BeanPropertyRowMapper<>(Client.class));
        return client;
    }

    public void addNewShipperInUserRole(String phone) {
        String sql = QueryCode.ADD_INTO_USER_ROLE;
        jdbcTemplate.update(sql, new Object[]{phone, Status.SHIPPER_ROLE});
    }

    public void addNewClientInUserRole(String phone) {
        String sql = QueryCode.ADD_INTO_USER_ROLE;
        jdbcTemplate.update(sql, new Object[]{phone, Status.CLIENT_ROLE});
    }

    public List<String> getAllUnabledShippers() {
        String sql = QueryCode.GET_ALL_UNENABLED_SHIPPERS;
        List<String> shipperLi = jdbcTemplate.queryForList(sql, new Object[]{Status.UNABLED_SHIPPER}, String.class);
        return shipperLi;
    }
    public void adminLoginLog(String phone){
        String sql = QueryCode.LOGIN_LOG;
        long timestamp = Calendar.getInstance().getTimeInMillis();
        String time_in = Tasks.formatDate(new Date());
        jdbcTemplate.update(sql, new Object[]{phone, time_in, timestamp, Status.ADMIN_ROLE});
    }

}
