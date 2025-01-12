package com.contentfarm.blog.service.container;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.contentfarm.blog.service.persistence"})
@EntityScan(basePackages = { "com.contentfarm.blog.service.persistence"})
@SpringBootApplication(scanBasePackages = "com.contentfarm")
public class BlogContainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogContainerApplication.class, args);
	}

}
