package com.vimensa.ship.shipper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class APIStart {
    private static final Logger LOGGER = LoggerFactory.getLogger(APIStart.class);

    public static void main(String[] args) {
        SpringApplication.run(APIStart.class, args);
        LOGGER.info(APIStart.class.getName() + " spring boot has started");
    }
}
