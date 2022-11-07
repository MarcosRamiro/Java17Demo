package com.ramiro.java17demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.ramiro.java17demo.record.Person;
import com.ramiro.java17demo.record.Result;
import com.ramiro.java17demo.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {

	private final RestTemplate restTemplate;
	private static final String URL_PEOPLE = "https://swapi.dev/api/people";

	@Autowired
	public ApiServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Person> getPeople() {

		Result result = callApi(URL_PEOPLE, Result.class);

		List<Person> people = result.results();

		while (result.hasNext()) {
			result = callApi(result.next(), Result.class);
			people.addAll(result.results());
		}

		return people;
	}

	@Override
	public Person getPerson(Integer id) {

		return callApi(String.format("%s/%d", URL_PEOPLE, id), Person.class);

	}

	private <T> T callApi(String url, Class<T> classz) {

		ResponseEntity<T> entity = restTemplate.getForEntity(url, classz);

		return nonNull(entity.getBody());

	}
	
	private static <T> T nonNull(@Nullable T result) {
		Assert.state(result != null, "No result");
		return result;
	}

}
