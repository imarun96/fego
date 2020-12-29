package com.fego.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class FegoUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(FegoUserApplication.class, args);
		log.info("Fego User Application has been started.");
	}

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}
}