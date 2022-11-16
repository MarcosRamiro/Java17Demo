package com.ramiro.java17demo.service;

import com.ramiro.java17demo.service.impl.RestClientBuilder;

public interface RestClient {
	
	<T> T get(Class<T> classz);
	
	<T> T get(String path, String query, Class<T> classz);
	
	RestClientBuilder path(String path);
	
	RestClientBuilder query(String query);

}
