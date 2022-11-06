package com.ramiro.java17demo.record;

public record Produto(String nome, String descricao) {

	public Produto{
		
		if (nome.equalsIgnoreCase("danone")) {
			throw new IllegalArgumentException(String.format("O conteúdo \"%s\" não é permitido", nome));
		}
		
	}
}
