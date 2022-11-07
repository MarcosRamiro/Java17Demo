package com.ramiro.java17demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramiro.java17demo.record.Produto;

@RestController
@RequestMapping("/home")
public class HelloController {
	
	private static final String MSG_TA_BEM_BARATO = "Tá bem barato";

	@GetMapping
	public String home() {
		
		return "olá mundo";
		
	}
	
	@GetMapping("/produto")
	public ResponseEntity<List<String>> produto() {
		
		List<Produto> produtos = List.of(
				new Produto("Bolacha", MSG_TA_BEM_BARATO),
				new Produto("Sabonete", MSG_TA_BEM_BARATO),
				new Produto("Alho", MSG_TA_BEM_BARATO),
				new Produto("Danone1", "Tá caro")
				);
		
		List<String> produtosFiltrado = 
		produtos.stream()
			.map(Produto::nome)
			.filter(nomeProduto -> !nomeProduto.equalsIgnoreCase("Pão"))
			.map(String::toUpperCase)
			.toList();
			
			
		return new ResponseEntity<>(produtosFiltrado, HttpStatus.OK);
		
	}
	
}
