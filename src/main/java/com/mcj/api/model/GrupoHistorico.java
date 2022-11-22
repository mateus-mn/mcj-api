package com.mcj.api.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GrupoHistorico
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Grupo grupo;

	@ManyToOne
	private Situacao situacao;

	private LocalDateTime dataHoraRegistro = LocalDateTime.now();

	@ManyToOne
	private Usuario usuarioRegistro;

	public GrupoHistorico()
	{
	}

	public GrupoHistorico(Grupo grupo, Situacao situacao, Usuario usuarioRegistro)
	{
		this.grupo           = grupo;
		this.situacao        = situacao;
		this.usuarioRegistro = usuarioRegistro;
	}

	public Long getId()
	{
		return this.id;
	}

	public Grupo getGrupo()
	{
		return this.grupo;
	}
	
	public Situacao getSituacao()
	{
		return this.situacao;
	}

	public Usuario getUsuarioRegistro()
	{
		return this.usuarioRegistro;
	}
	
	public LocalDateTime getDataHoraRegistro()
	{
		return this.dataHoraRegistro;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setGrupo(Grupo grupo)
	{
		this.grupo = grupo;
	}

	public void setSituacao(Situacao situacao)
	{
		this.situacao = situacao;
	}

	public void setUsuarioRegistro(Usuario usuarioRegistro)
	{
		this.usuarioRegistro = usuarioRegistro;
	}

	public void setDataHoraRegistro(LocalDateTime dataHoraRegistro)
	{
		this.dataHoraRegistro = dataHoraRegistro;
	}
}
