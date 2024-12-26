package com.ohgiraffers.semiproject.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ohgiraffers.semiproject.messenger.model.dao")

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
