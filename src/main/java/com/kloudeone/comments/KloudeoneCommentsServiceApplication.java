package com.kloudeone.comments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
 public class KloudeoneCommentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KloudeoneCommentsServiceApplication.class, args);
	}
	

}
