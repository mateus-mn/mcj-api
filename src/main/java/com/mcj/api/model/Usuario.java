package com.mcj.api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
public class Usuario implements UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	private String login;

	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfil = new ArrayList<>();

	private Boolean ativo = true;

	public Usuario()
	{
	}

	public Usuario(Long id)
	{
		this.id = id;
	}

	public Usuario(String nome, String login, String senha, List<Perfil> perfis)
	{
		this.nome   = nome;
		this.login  = login;
		this.senha  = senha;
		this.perfil = perfis;
	}

	public Usuario(Long id, String nome)
	{
		this.id   = id;
		this.nome = nome;
	}

	public Long getId()
	{
		return this.id;
	}
	
	public String getNome()
	{
		return this.nome;
	}
	
	public String getLogin()
	{
		return this.login;
	}
	
	public String getSenha()
	{
		return this.senha;
	}

	public List<Perfil> getPerfil()
	{
		return this.perfil;
	}

	public Boolean getAtivo()
	{
		return this.ativo;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public void setPerfil(List<Perfil> perfil)
	{
		this.perfil = perfil;
	}

	public void setAtivo(Boolean ativo)
	{
		this.ativo = ativo;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return this.perfil;
	}

	@Override
	public String getPassword()
	{
		return this.senha;
	}
	
	@Override
	public String getUsername()
	{
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		if(this.ativo == null)
		{
			return false;
		}
		else
		{
			return this.ativo;
		}
	}
}