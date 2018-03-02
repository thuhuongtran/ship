package com.vimensa.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class RunAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(RunAPI.class);

    public static void main(String[] args) {
        SpringApplication.run(RunAPI.class, args);
        LOGGER.info(RunAPI.class.getName() + " spring boot has started");
    }
}
