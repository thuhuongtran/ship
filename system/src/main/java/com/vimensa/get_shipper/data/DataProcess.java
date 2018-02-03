package com.vimensa.get_shipper.data;

import com.vimensa.get_shipper.RunAPI;
import com.vimensa.get_shipper.dao.Shipper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
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

}
