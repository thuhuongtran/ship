package com.vimensa.get_shipper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class RunAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunAPI.class);

    public static void main(String[] args) {
        SpringApplication.run(RunAPI.class, args);
        LOGGER.info(RunAPI.class.getName() + " spring boot has started");
    }
}
