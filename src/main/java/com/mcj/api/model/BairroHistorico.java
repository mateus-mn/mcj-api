package com.mcj.api.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BairroHistorico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Bairro bairro;

	@ManyToOne
	private Situacao situacao;

	private LocalDateTime dataHoraRegistro = LocalDateTime.now();

	@ManyToOne
	private Usuario usuarioRegistro;

	public BairroHistorico() {
	}

	public BairroHistorico(Bairro bairro, Situacao situacao, Usuario usuarioRegistro) {
		this.bairro = bairro;
		this.situacao = situacao;
		this.usuarioRegistro = usuarioRegistro;
	}

	public Long getId() {
		return this.id;
	}

	public Bairro getBairro() {
		return this.bairro;
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

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
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
