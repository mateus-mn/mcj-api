package com.mcj.api.dto;

import com.mcj.api.model.Grupo;

public class GrupoDto {
	private Long id;
	private Long numero;
	private String nome;
	private Boolean ativo;

	public GrupoDto() {
	}

	public GrupoDto(Grupo grupo) {
		this.id = grupo.getId();
		this.numero = grupo.getNumero();
		this.nome = grupo.getNome();
		this.ativo = grupo.getAtivo();
	}

	public Long getId() {
		return this.id;
	}

	public Long getNumero() {
		return this.numero;
	}

	public String getNome() {
		return this.nome;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}
}
