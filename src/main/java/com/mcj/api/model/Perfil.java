package com.mcj.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority
{
	// PARÂMETROS
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String descricao;
	// FIM DOS PARÂMETROS

	// CONSTRUTORES
	public Perfil()
	{
	}

	public Perfil(Long id)
	{
		this.id = id;
	}
	// FIM DOS CONSTRUTORES

	// GETTERS
	public Long getId()
	{
		return this.id;
	}

	public String getDescricao()
	{
		return this.descricao;
	}
	// FIM DOS GETTERS

	// SETTERS
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}
	// FIM DOS SETTERS

	// MÉTODOS DE SOBRECARGA
	@Override
	public String getAuthority()
	{
		return this.descricao;
	}
	// FIM DOS MÉTODOS DE SOBRCARGA
}