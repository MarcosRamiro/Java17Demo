package com.ramiro.java17demo.controller;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Person> people(@RequestParam(required = false) String name) {

		List<Person> people = getPeople();

		if (name != null) {
			return people.stream().filter(person -> person.name().toLowerCase().contains(name.toLowerCase())).toList();

		}

		return people;

	}

	@GetMapping
	@RequestMapping("/people/{id}")
	public Person person(@PathVariable Integer id) {

		return apiService.getPerson(id);

	}

	@GetMapping
	@RequestMapping("/people/groupby")
	public Map<String, Set<String>> groupBy() {

		List<Person> people = getPeople();

		//return people.stream().collect(Collectors.groupingBy(Person::skin_color));
		//return people.stream().collect(Collectors.groupingBy(Person::skin_color, Collectors.summingInt(p -> p.films().size())));
		return people.stream().collect(groupingBy(Person::skinColor, mapping(Person::name, toSet())));

	}

	private List<Person> getPeople() {
		return apiService.getPeople();
	}

}
