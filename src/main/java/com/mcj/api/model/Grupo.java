package com.mcj.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Grupo
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long numero;

	private String nome;

	private Boolean ativo = true;

	public Grupo()
	{

	}

	public Grupo(Long numero, String nome)
	{
		this.numero = numero;
		this.nome   = nome;
	}

	public Long getId()
	{
		return this.id;
	}

	public Long getNumero()
	{
		return this.numero;
	}

	public String getNome()
	{
		return this.nome;
	}

	public Boolean getAtivo()
	{
		return this.ativo;
	}

	public void setNumero(Long numero)
	{
		this.numero = numero;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public void setAtivo(Boolean ativo)
	{
		this.ativo = ativo;
	}
}