package com.jjarfi.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class DemiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemiApplication.class, args);
	}

}
