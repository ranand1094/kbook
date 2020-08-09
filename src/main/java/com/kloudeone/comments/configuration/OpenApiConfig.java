package com.kloudeone.comments.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	
	@Bean
	public GroupedOpenApi publicApi() {
		String[] packagedToMatch = { "com.kloudeone.comments" };
		return GroupedOpenApi.builder().setGroup("kbook-comments-api").packagesToScan(packagedToMatch).pathsToMatch("/**")
				.build();
	}
	

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components()).info(new Info()
				.title("KBook")
				.version("v1.0")
				.description("API for kloud book"));
	}
}