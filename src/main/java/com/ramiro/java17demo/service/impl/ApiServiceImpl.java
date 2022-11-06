package com.ramiro.java17demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ramiro.java17demo.record.Person;
import com.ramiro.java17demo.record.Result;
import com.ramiro.java17demo.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {

	private final RestTemplate restTemplate;
	private final String URL_PEOPLE = "https://swapi.dev/api/people";
	
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
		
		Person person = callApi(String.format("%s/%d", URL_PEOPLE, id), Person.class);
		
		return person;
	}

	private <T> T callApi(String url, Class<T> classz){
		
		ResponseEntity<T> entity = restTemplate.getForEntity(url, classz);
		
		if (entity.hasBody())
			return entity.getBody();
		
		throw new RuntimeException(
				String.format("Ocorreu o erro [StatusCode %s] na chamada [URL %s]."
						,String.valueOf(entity.getStatusCodeValue())
						,url)
				);
		
	}
	
}
