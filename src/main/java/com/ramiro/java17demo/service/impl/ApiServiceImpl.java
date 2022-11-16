package com.ramiro.java17demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramiro.java17demo.record.Person;
import com.ramiro.java17demo.record.Result;
import com.ramiro.java17demo.service.ApiService;
import com.ramiro.java17demo.service.RestClient;

@Service
public class ApiServiceImpl implements ApiService {

	private final RestClient restClient;
	private static final String PEOPLE_VALUE = "people";
	private static final int QUERY_PARAMETERS = 1;

	@Autowired
	public ApiServiceImpl(RestClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public List<Person> getPeople() {

		Result result;

		result = restClient.path(PEOPLE_VALUE).get(Result.class);

		List<Person> people = result.results();

		while (result.hasNext()) {

			String query = result.next().split("\\?")[QUERY_PARAMETERS];

			result = restClient.path(PEOPLE_VALUE).query(query).get(Result.class);
			people.addAll(result.results());
		}
		return people;
	}

	@Override
	public Person getPerson(Integer id) {

		return restClient.path(PEOPLE_VALUE).path(String.format("%d", id)).get(Person.class);

	}

}
