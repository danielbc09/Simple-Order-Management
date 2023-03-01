package com.dany.order.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.dany.order.management",
		"com.dany.order.management.product",
		"com.dany.order.management.customer",
		"com.dany.order.management.order"})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
