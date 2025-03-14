package com.contentfarm.blog.service.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableDiscoveryClient
@EnableFeignClients
@EnableJpaRepositories(basePackages = { "com.contentfarm.blog.service.persistence"})
@EntityScan(basePackages = { "com.contentfarm.blog.service.persistence" })
@SpringBootApplication(scanBasePackages = {"com.contentfarm.blog.service"})
public class BlogContainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogContainerApplication.class, args);
	}

}
