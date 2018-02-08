package com.vimensa.ship.shipper.authentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://171.244.10.52:3306/ship");
        driverManagerDataSource.setUsername("misa");
        driverManagerDataSource.setPassword("VnDgV5*A8m2k16L>?");
        return driverManagerDataSource;
    }

}
