package com.mcj.api.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UsuarioHistorico
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Usuario usuario;

	@ManyToOne
	private Situacao situacao;

	private LocalDateTime dataHoraRegistro = LocalDateTime.now();

	@ManyToOne
	private Usuario usuarioRegistro;

	public UsuarioHistorico()
	{
	}

	public UsuarioHistorico(Usuario usuario, Situacao situacao, Usuario usuarioRegistro)
	{
		this.usuario         = usuario;
		this.situacao        = situacao;
		this.usuarioRegistro = usuarioRegistro;
	}

	public Long getId()
	{
		return this.id;
	}

	public Usuario getUsuario()
	{
		return this.usuario;
	}
	
	public Situacao getSituacao()
	{
		return this.situacao;
	}
	
	public LocalDateTime getDataHoraRegistro()
	{
		return this.dataHoraRegistro;
	}

	public Usuario getUsuarioRegistro()
	{
		return this.usuarioRegistro;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public void setSituacao(Situacao situacao)
	{
		this.situacao = situacao;
	}

	public void setDataHoraRegistro(LocalDateTime dataHoraRegistro)
	{
		this.dataHoraRegistro = dataHoraRegistro;
	}

	public void setUsuarioRegistro(Usuario usuarioRegistro)
	{
		this.usuarioRegistro = usuarioRegistro;
	}
}