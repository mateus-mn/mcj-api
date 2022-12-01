package com.mcj.api.dto;

import com.mcj.api.model.Sexo;

public class SexoDto {
	private Long id;
	private String descricao;
	private Character sigla;

	public SexoDto() {
	}

	public SexoDto(Sexo sexo) {
		this.id = sexo.getId();
		this.descricao = sexo.getDescricao();
		this.sigla = sexo.getSigla();
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
}
