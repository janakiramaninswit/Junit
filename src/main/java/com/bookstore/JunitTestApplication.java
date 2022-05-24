package com.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

@SpringBootApplication
@ComponentScan("com.*")
public class JunitTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunitTestApplication.class, args);
	}
	 
}
