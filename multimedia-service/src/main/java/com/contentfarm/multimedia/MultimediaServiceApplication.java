package com.contentfarm.multimedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.contentfarm.multimedia"}, exclude = {DataSourceAutoConfiguration.class})
public class MultimediaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultimediaServiceApplication.class, args);
    }

}
