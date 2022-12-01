package com.mcj.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Sexo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	private Character sigla;

	public Sexo() {
	}

	public Long getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public Character getSigla() {
		return this.sigla;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setSigla(Character sigla) {
		this.sigla = sigla;
	}
}
