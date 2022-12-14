package com.mcj.api.dto;

import com.mcj.api.model.Pais;

public class PaisDto {
	private Long id;
	private String nome;
	private String sigla;

	public PaisDto() {
	}

	public PaisDto(Pais pais) {
		this.id = pais.getId();
		this.nome = pais.getNome();
		this.sigla = pais.getSigla();
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getSigla() {
		return this.sigla;
	}
}
