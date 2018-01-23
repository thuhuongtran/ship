package com.vimensa.ship.client.data;

import com.vimensa.ship.client.dao.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Transactional
@Repository
public class DataProcessImp{
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public DataProcessImp(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    /**
     * register client
     * @param phone
     * @param code
     * */
    public void registerClient(String phone, String code){
        String sql = QueryCode.REGISTER_CLIENT;
        jdbcTemplate.update(sql, new Object[]{phone, code});
    }
    /**
     * get client by phone
     * @param phone
     * */
    public Client getClientByPhone(String phone){
        String sql = QueryCode.GET_CLIENT_BY_PHONE+phone;
        Client client = jdbcTemplate.queryForObject(sql, Client.class);
        return client;
    }
}
