package com.mcj.api.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CasalEndereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String logradouro;

	private Long numero;

	@ManyToOne
	private Bairro bairro;

	@ManyToOne
	private Casal casal;

	private LocalDateTime dataHoraCadastro = LocalDateTime.now();

	private Boolean ativo = true;

	public CasalEndereco() {
	}

	public CasalEndereco(Casal casal, String logradouro, Long numero, Bairro bairro, Boolean ativo) {
		this.casal = casal;
		this.logradouro = logradouro;
		this.numero = numero;
		this.bairro = bairro;
		this.ativo = ativo;
	}

	public Long getId() {
		return this.id;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public Long getNumero() {
		return this.numero;
	}

	public Bairro getBairro() {
		return this.bairro;
	}

	public Casal getCasal() {
		return this.casal;
	}

	public LocalDateTime getDataHoraCadastro() {
		return this.dataHoraCadastro;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public void setCasal(Casal casal) {
		this.casal = casal;
	}

	public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}