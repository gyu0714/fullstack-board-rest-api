package com.spring.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.spring.board.apiController",
			"com.spring.board.service", "com.spring.board.dto"})
@EntityScan(basePackages = {"com.spring.board.entity"})
@EnableJpaRepositories(basePackages = {"com.spring.board.repository"})
public class BoardRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardRestApiApplication.class, args);
	}

}
