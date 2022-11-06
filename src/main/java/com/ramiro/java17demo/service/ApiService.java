package com.ramiro.java17demo.service;

import java.util.List;

import com.ramiro.java17demo.record.Person;

public interface ApiService {

	List<Person> getPeople();

	Person getPerson(Integer id);

}
