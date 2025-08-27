package com.foodapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.foodapp.repository")
@SpringBootApplication
public class FoodServiceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodServiceAppApplication.class, args);
	}

}
