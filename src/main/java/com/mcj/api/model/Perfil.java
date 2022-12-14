package com.mcj.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;

	public Perfil()
	{
	}

	public Perfil(Long id)
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
	
	@Override
	public String getAuthority()
	{
		return this.descricao;
	}
}