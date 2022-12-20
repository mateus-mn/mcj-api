package com.mcj.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcj.api.model.Casal;
import com.mcj.api.model.CasalEndereco;

public class CasalDto {
	private Long id;
	private Long idHomem;
	private String nomeHomem;
	private Long idMulher;
	private String nomeMulher;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataCasamento;
	private Long idCasalPadrinho;
	private String homemCasalPadrinho;
	private String mulherCasalPadrinho;
	private String logradouro;
	private Long numero;
	private Long idBairro;
	private String nomeBairro;
	private Long idCidade;
	private String nomeCidade;
	private Long idEstado;
	private String nomeEstado;
	private String siglaEstado;
	private Long idPais;
	private String nomePais;
	private String siglaPais;
	private Boolean ativo;

	public CasalDto() {
	}

	public CasalDto(Casal casal, CasalEndereco endereco) {
		this.id = casal.getId();
		this.idHomem = casal.getHomem().getId();
		this.nomeHomem = casal.getHomem().getNome();
		this.idMulher = casal.getMulher().getId();
		this.nomeMulher = casal.getMulher().getNome();
		this.dataCasamento = casal.getDataCasamento();

		if (casal.getCasalPadrinho() != null) {
			this.idCasalPadrinho = casal.getCasalPadrinho().getId();
			this.homemCasalPadrinho = casal.getCasalPadrinho().getHomem().getNome();
			this.mulherCasalPadrinho = casal.getCasalPadrinho().getMulher().getNome();
		}

		if (endereco != null) {
			this.logradouro = endereco.getLogradouro();
			this.numero = endereco.getNumero();
			this.idBairro = endereco.getBairro().getId();
			this.nomeBairro = endereco.getBairro().getNome();
			this.idCidade = endereco.getBairro().getCidade().getId();
			this.nomeCidade = endereco.getBairro().getCidade().getNome();
			this.idEstado = endereco.getBairro().getCidade().getEstado().getId();
			this.nomeEstado = endereco.getBairro().getCidade().getEstado().getNome();
			this.siglaEstado = endereco.getBairro().getCidade().getEstado().getSigla();
			this.idPais = endereco.getBairro().getCidade().getEstado().getPais().getId();
			this.nomePais = endereco.getBairro().getCidade().getEstado().getPais().getNome();
			this.siglaPais = endereco.getBairro().getCidade().getEstado().getPais().getSigla();
		}

		this.ativo = casal.getAtivo();
	}

	public Long getId() {
		return this.id;
	}

	public Long getIdHomem() {
		return this.idHomem;
	}

	public String getNomeHomem() {
		return this.nomeHomem;
	}

	public Long getIdMulher() {
		return this.idMulher;
	}

	public String getNomeMulher() {
		return this.nomeMulher;
	}

	public LocalDate getDataCasamento() {
		return this.dataCasamento;
	}

	public Long getIdCasalPadrinho() {
		return this.idCasalPadrinho;
	}

	public String getHomemCasalPadrinho() {
		return this.homemCasalPadrinho;
	}

	public String getMulherCasalPadrinho() {
		return this.mulherCasalPadrinho;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public Long getNumero() {
		return this.numero;
	}

	public Long getIdBairro() {
		return this.idBairro;
	}

	public String getNomeBairro() {
		return this.nomeBairro;
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
