package com.kloudeone.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class KloudeoneCommentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KloudeoneCommentsServiceApplication.class, args);
	}
	

	@Bean
	public  ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

}
