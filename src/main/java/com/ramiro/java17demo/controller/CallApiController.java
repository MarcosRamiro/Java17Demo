package com.ramiro.java17demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramiro.java17demo.record.Person;
import com.ramiro.java17demo.service.ApiService;

@RestController
@RequestMapping("/api")
public class CallApiController {

	private final ApiService apiService;

	@Autowired
	public CallApiController(ApiService apiService) {
		this.apiService = apiService;
	}

	@GetMapping
	@RequestMapping("/people")
	public ResponseEntity<List<Person>> people(@RequestParam(required = false) String name) {

		List<Person> people = apiService.getPeople();

		if (name != null) {
			List<Person> peopleFiltred = people.stream()
					.filter(person -> person.name().toLowerCase().contains(name.toLowerCase()))
					.collect(Collectors.toList());

			return new ResponseEntity<>(peopleFiltred, HttpStatus.OK);
		}

		return new ResponseEntity<>(people, HttpStatus.OK);

	}


	@GetMapping
	@RequestMapping("/people/{id}")
	public ResponseEntity<Person> person(@PathVariable Integer id) {

		Person person = apiService.getPerson(id);

		return new ResponseEntity<>(person, HttpStatus.OK);

	}

}
