package com.mcj.api.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Casal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Pessoa homem;

	@ManyToOne
	private Pessoa mulher;

	private LocalDate dataCasamento;

	@ManyToOne
	private Casal casalPadrinho;

	private Boolean ativo = true;

	public Casal() {
	}

	public Casal(Pessoa homem, Pessoa mulher, LocalDate dataCasamento, Casal casalPadrinho) {
		this.homem = homem;
		this.mulher = mulher;
		this.dataCasamento = dataCasamento;
		this.casalPadrinho = casalPadrinho;
	}

	public Long getId() {
		return this.id;
	}

	public Pessoa getHomem() {
		return this.homem;
	}

	public Pessoa getMulher() {
		return this.mulher;
	}

	public LocalDate getDataCasamento() {
		return this.dataCasamento;
	}

	public Casal getCasalPadrinho() {
		return this.casalPadrinho;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setHomem(Pessoa homem) {
		this.homem = homem;
	}

	public void setMulher(Pessoa mulher) {
		this.mulher = mulher;
	}

	public void setDataCasamento(LocalDate dataCasamento) {
		this.dataCasamento = dataCasamento;
	}

	public void setCasalPadrinho(Casal casalPadrinho) {
		this.casalPadrinho = casalPadrinho;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
