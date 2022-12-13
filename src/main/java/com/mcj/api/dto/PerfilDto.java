package com.mcj.api.dto;

import com.mcj.api.model.Perfil;

public class PerfilDto {
	private Long id;
	private String descricao;

	public PerfilDto() {
	}

	public PerfilDto(Perfil perfil) {
		this.id = perfil.getId();
		this.descricao = perfil.getDescricao();
	}

	public Long getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}
}
