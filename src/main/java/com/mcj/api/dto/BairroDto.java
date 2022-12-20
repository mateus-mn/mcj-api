package com.mcj.api.dto;

import com.mcj.api.model.Bairro;

public class BairroDto {
	private Long id;
	private String nome;
	private Long idCidade;
	private String nomeCidade;
	private Long idEstado;
	private String nomeEstado;
	private String siglaEstado;
	private Long idPais;
	private String nomePais;
	private String siglaPais;
	private Boolean ativo;

	public BairroDto() {
	}

	public BairroDto(Bairro bairro) {
		this.id = bairro.getId();
		this.nome = bairro.getNome();
		this.idCidade = bairro.getCidade().getId();
		this.nomeCidade = bairro.getCidade().getNome();
		this.idEstado = bairro.getCidade().getEstado().getId();
		this.nomeEstado = bairro.getCidade().getEstado().getNome();
		this.siglaEstado = bairro.getCidade().getEstado().getSigla();
		this.idPais = bairro.getCidade().getEstado().getPais().getId();
		this.nomePais = bairro.getCidade().getEstado().getPais().getNome();
		this.siglaPais = bairro.getCidade().getEstado().getPais().getSigla();
		this.ativo = bairro.getAtivo();
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public Long getIdCidade() {
		return this.idCidade;
	}

	public String getNomeCidade() {
		return this.nomeCidade;
	}

	public Long getIdEstado() {
		return this.idEstado;
	}

	public String getNomeEstado() {
		return this.nomeEstado;
	}

	public String getSiglaEstado() {
		return this.siglaEstado;
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

	public Boolean getAtivo() {
		return this.ativo;
	}
}
