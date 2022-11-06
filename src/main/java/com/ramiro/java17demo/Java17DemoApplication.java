package com.ramiro.java17demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.ramiro.java17demo.handler.MyErrorHandler;

@SpringBootApplication
public class Java17DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java17DemoApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		RestTemplate restTemplate = builder.build();
		restTemplate.setErrorHandler(new MyErrorHandler());
		return restTemplate;
	}

}
