package com.example.Auxo_ECommerce_API;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {
		org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
@EnableJpaRepositories(basePackages = "com.example.Auxo_ECommerce_API.Domain.Interfaces")
@ComponentScan(basePackages = "com.example.Auxo_ECommerce_API")

public class Auxo_ECommerce_API {
	public static void main(String[] args) {
		SpringApplication.run(Auxo_ECommerce_API.class, args);
	}
}