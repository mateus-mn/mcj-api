package com.mcj.api.dto;

import com.mcj.api.model.Cidade;

public class CidadeDto {
	private Long id;
	private String nome;
	private Long idEstado;
	private String nomeEstado;
	private Long idPais;
	private String nomePais;

	public CidadeDto() {
	}

	public CidadeDto(Cidade cidade) {
		this.id = cidade.getId();
		this.nome = cidade.getNome();
		this.idEstado = cidade.getEstado().getId();
		this.nomeEstado = cidade.getEstado().getNome();
		this.idPais = cidade.getEstado().getPais().getId();
		this.nomePais = cidade.getEstado().getPais().getNome();
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public Long getIdEstado() {
		return this.idEstado;
	}

	public String getNomeEstado() {
		return this.nomeEstado;
	}

	public Long getIdPais() {
		return this.idPais;
	}

	public String getNomePais() {
		return this.nomePais;
	}
}
