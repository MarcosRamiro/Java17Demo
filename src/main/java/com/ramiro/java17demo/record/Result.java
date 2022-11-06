package com.ramiro.java17demo.record;

import java.util.List;

public record Result(String next, List<Person> results) {
	
	public boolean hasNext() {
		return this.next != null;
	}

}
