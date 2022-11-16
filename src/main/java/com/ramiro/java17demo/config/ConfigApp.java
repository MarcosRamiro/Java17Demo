package com.ramiro.java17demo.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.ramiro.java17demo.handler.MyErrorHandler;
import com.ramiro.java17demo.service.RestClient;
import com.ramiro.java17demo.service.impl.RestClientImpl;

@Configuration
public class ConfigApp {

	@Bean
	public RestClient restClient(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		restTemplate.setErrorHandler(new MyErrorHandler());
		return new RestClientImpl("https://swapi.dev/api", restTemplate);
	}
}
