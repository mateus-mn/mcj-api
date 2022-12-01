package com.mcj.api.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PessoaEmail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	@ManyToOne
	private Pessoa pessoa;

	private LocalDateTime dataHoraCadastro = LocalDateTime.now();

	private Boolean ativo = true;

	public PessoaEmail() {
	}

	public PessoaEmail(Pessoa pessoa, String email, Boolean ativo) {
		this.pessoa = pessoa;
		this.email = email;
		this.ativo = ativo;
	}

	public Long getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
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

	public void setEmail(String email) {
		this.email = email;
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