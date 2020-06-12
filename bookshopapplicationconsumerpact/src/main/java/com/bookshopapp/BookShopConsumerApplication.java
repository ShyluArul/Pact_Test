package com.bookshopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.bookshopapp")
public class BookShopConsumerApplication {
	public static void main(String[] args) {
		SpringApplication.run(BookShopConsumerApplication.class, args);
	}
}
