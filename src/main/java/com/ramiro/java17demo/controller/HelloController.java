package com.ramiro.java17demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramiro.java17demo.record.Produto;

@RestController
@RequestMapping("/home")
public class HelloController {
	

	@GetMapping
	public String home() {
		
		return "olá mundo";
		
	}
	
	@GetMapping("/produto")
	public ResponseEntity<List<String>> produto() {
		
		List<Produto> produtos = List.of(
				new Produto("Bolacha", "Tá bem barato"),
				new Produto("Sabonete", "Tá bem barato"),
				new Produto("Alho", "Tá bem barato"),
				new Produto("Danone1", "Tá caro")
				);
		
		List<String> produtosFiltrado = 
		produtos.stream()
			.map(produto -> produto.nome())
			.filter(nomeProduto -> !nomeProduto.equalsIgnoreCase("Pão"))
			.map(String::toUpperCase)
			.collect(Collectors.toList());
			
			
		return new ResponseEntity<>(produtosFiltrado, HttpStatus.OK);
		
	}
	
}
