package com.duetche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class DeutcheApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeutcheApplication.class, args);
	}
}
