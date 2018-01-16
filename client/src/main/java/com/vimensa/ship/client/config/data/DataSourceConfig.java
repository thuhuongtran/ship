package com.vimensa.ship.client.config.data;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://171.244.27.229:3306/ship-client");
//        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.username("misa");
        dataSourceBuilder.password("Vp@abc123");
        return dataSourceBuilder.build();
    }
}
