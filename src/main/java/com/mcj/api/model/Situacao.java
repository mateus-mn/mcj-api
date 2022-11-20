package com.mcj.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Situacao
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	public Situacao()
	{
	}

	public Situacao(Long id)
	{
		this.id = id;
	}

	public Long getId()
	{
		return this.id;
	}
	
	public String getDescricao()
	{
		return this.descricao;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
}