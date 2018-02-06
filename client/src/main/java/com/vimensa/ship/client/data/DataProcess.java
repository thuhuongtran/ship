package com.vimensa.ship.client.data;

import com.vimensa.ship.client.APIStart;
import com.vimensa.ship.client.dao.Client;
import com.vimensa.ship.client.dao.Shipper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Timestamp;

@Transactional
@Repository
public class DataProcess {
    private final static Logger logger = LoggerFactory.getLogger(APIStart.class);
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public DataProcess(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    /**
     * register client - fix later
     * @param phone
     * @param code
     * */
    public void registerClient(String phone, String code){
        String sql = QueryCode.REGISTER_CLIENT;
        jdbcTemplate.update(sql, new Object[]{phone, code});
        logger.info(DataProcess.class.getName()+" insert successfully.");
    }
    /**
     * get client by phone
     * @param phone
     * */
    public Client getClientByPhone(String phone){
        String sql = QueryCode.GET_CLIENT_BY_PHONE;
        Client client = jdbcTemplate.queryForObject(sql, new Object[]{phone}, new BeanPropertyRowMapper<>(Client.class));
        return client;
    }
  /**
   * get shipper by shipper-phone
   * @param phone
   * */
  public Shipper getShipperByPhone(String phone){
      String sql = QueryCode.GET_SHIPPER_BY_PHONE;
      Shipper shipper = jdbcTemplate.queryForObject(sql, new Object[]{phone}, new BeanPropertyRowMapper<>(Shipper.class));
      return shipper;
  }

}
