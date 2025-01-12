package com.contentfarm.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.contentfarm"})
public class ContentFarmServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentFarmServiceApplication.class, args);
    }

}
