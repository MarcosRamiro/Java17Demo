package com.ramiro.java17demo.service.impl;

import com.ramiro.java17demo.service.RestClient;

public class RestClientBuilder {

	private final RestClient restClient;
	private String path;
	private String query;

	public RestClientBuilder(RestClient restClient, String path, String query) {
		this.restClient = restClient;
		this.path = sanitizePath(path);
		this.query = query;
	}

	private String sanitizePath(String path) {
		if (path.equals(""))
			return path;
		String newPath = path.replace("/", "");
		return "/" + newPath;
	}

	public RestClientBuilder path(String path) {
		this.path = this.path + sanitizePath(path);
		return this;
	}

	public RestClientBuilder query(String query) {
		this.query = query;
		return this;
	}

	public <T> T get(Class<T> classz) {
		return restClient.get(path, query, classz);
	}

}
