package com.mcj.api.dto;

public class TokenDto
{
	private String token;	
	private String tipo;
	private Boolean status;
	private Long idUsuario;
	private String nomeUsuario;

	public TokenDto()
	{
	}

	public TokenDto(String token, String tipo, Boolean status, Long idUsuario, String nomeUsuario)
	{
		this.token       = token;
		this.tipo        = tipo;
		this.status      = status;
		this.idUsuario   = idUsuario;
		this.nomeUsuario = nomeUsuario;
	}

	public String getToken()
	{
		return this.token;
	}

	public String getTipo()
	{
		return this.tipo;
	}

	public Boolean getStatus()
	{
		return this.status;
	}

	public Long getIdUsuario()
	{
		return this.idUsuario;
	}

	public String getNomeUsuario()
	{
		return this.nomeUsuario;
	}
}