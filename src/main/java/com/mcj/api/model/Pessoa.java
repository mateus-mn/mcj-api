package com.mcj.api.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@ManyToOne
	private Sexo sexo;

	private LocalDate dataNascimento;

	private Boolean ativo = true;

	public Pessoa() {
	}

	public Pessoa(String nome, Sexo sexo, LocalDate dataNascimento) {
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public Sexo getSexo() {
		return this.sexo;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
