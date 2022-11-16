package com.ramiro.java17demo.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.ramiro.java17demo.service.RestClient;

public class RestClientImpl implements RestClient {

	private final String urlBase;
	private final RestTemplate restTemplate;
	private static final String EMPTY_VALUE = "";
	private static final String QUESTION_VALUE = "?";

	public RestClientImpl(String urlBase, RestTemplate restTemplate) {
		this.urlBase = urlBase;
		this.restTemplate = restTemplate;
	}

	@Override
	public <T> T get(String path, String query, Class<T> classz) {

		String uri = this.urlBase + path + QUESTION_VALUE + query;

		ResponseEntity<T> entity = restTemplate.getForEntity(uri, classz);

		return nonNull(entity.getBody());

	}

	@Override
	public <T> T get(Class<T> classz) {

		return nonNull(this.get(EMPTY_VALUE, EMPTY_VALUE, classz));

	}

	private static <T> T nonNull(@Nullable T result) {
		Assert.state(result != null, "No result");
		return result;
	}

	@Override
	public RestClientBuilder path(String path) {
		return new RestClientBuilder(this, path, EMPTY_VALUE);

	}

	@Override
	public RestClientBuilder query(String query) {

		return new RestClientBuilder(this, EMPTY_VALUE, query);

	}

}
