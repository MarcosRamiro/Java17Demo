package com.ramiro.java17demo.record;

import java.io.Serializable;

public record Produto(String nome, String descricao) implements Serializable {

	public Produto{
		
		if (nome.equalsIgnoreCase("danone")) {
			throw new IllegalArgumentException(String.format("O conteúdo \"%s\" não é permitido", nome));
		}
		
	}
}
