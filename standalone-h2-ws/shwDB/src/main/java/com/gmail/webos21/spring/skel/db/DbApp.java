package com.gmail.webos21.spring.skel.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
public class DbApp {

	public static void main(String[] args) {
		SpringApplication.run(DbApp.class, args);
	}

}
