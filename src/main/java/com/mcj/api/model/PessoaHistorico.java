package com.mcj.api.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PessoaHistorico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Pessoa pessoa;

	@ManyToOne
	private Situacao situacao;

	private LocalDateTime dataHoraRegistro = LocalDateTime.now();

	@ManyToOne
	private Usuario usuarioRegistro;

	public PessoaHistorico() {
	}

	public PessoaHistorico(Pessoa pessoa, Situacao situacao, Usuario usuarioRegistro) {
		this.pessoa = pessoa;
		this.situacao = situacao;
		this.usuarioRegistro = usuarioRegistro;
	}

	public Long getId() {
		return this.id;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public Situacao getSituacao() {
		return this.situacao;
	}

	public Usuario getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public LocalDateTime getDataHoraRegistro() {
		return this.dataHoraRegistro;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public void setUsuarioRegistro(Usuario usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public void setDataHoraRegistro(LocalDateTime dataHoraRegistro) {
		this.dataHoraRegistro = dataHoraRegistro;
	}
}
