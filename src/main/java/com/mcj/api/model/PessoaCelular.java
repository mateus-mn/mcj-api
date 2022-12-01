package com.mcj.api.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PessoaCelular {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String celular;

	@ManyToOne
	private Pessoa pessoa;

	private LocalDateTime dataHoraCadastro = LocalDateTime.now();

	private Boolean ativo = true;

	public PessoaCelular() {
	}

	public PessoaCelular(Pessoa pessoa, String celular, Boolean ativo) {
		this.pessoa = pessoa;
		this.celular = celular;
		this.ativo = ativo;
	}

	public Long getId() {
		return this.id;
	}

	public String getCelular() {
		return this.celular;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
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

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setDataHoraCadastro(LocalDateTime dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
