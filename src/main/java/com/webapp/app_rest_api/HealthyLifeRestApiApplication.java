package com.webapp.app_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.webapp.app_rest_api.model"})
public class HealthyLifeRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthyLifeRestApiApplication.class, args);
    }

}
