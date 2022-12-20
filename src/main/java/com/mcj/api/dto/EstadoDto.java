package com.mcj.api.dto;

import com.mcj.api.model.Estado;

public class EstadoDto {
	private Long id;
	private String nome;
	private String sigla;
	private Long idPais;
	private String nomePais;
	private String siglaPais;

	public EstadoDto() {
	}

	public EstadoDto(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
		this.sigla = estado.getSigla();
		this.idPais = estado.getPais().getId();
		this.nomePais = estado.getPais().getNome();
		this.siglaPais = estado.getPais().getSigla();
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

	public Long getIdPais() {
		return this.idPais;
	}

	public String getNomePais() {
		return this.nomePais;
	}

	public String getSiglaPais() {
		return this.siglaPais;
	}
}
