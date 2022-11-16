package com.ramiro.java17demo.record;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public record Person(String name,
					String homeWorld, 
					List<String> films,
					String skinColor) implements Serializable {

	@JsonCreator
	public Person(
			@JsonProperty("name") String name,
			@JsonProperty("homeworld") String homeWorld,
			@JsonProperty("films") List<String> films,
			@JsonProperty("skin_color") String skinColor){
				
			this.name = name;
			this.homeWorld = homeWorld;
			this.films = films;
			this.skinColor = skinColor;
			
	}

}
