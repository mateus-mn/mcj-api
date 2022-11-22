package com.mcj.api.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcj.api.model.GrupoHistorico;

public class GrupoHistoricoDto
{
	private Long id;	
	private Long idSituacao;
	private String descricaoSituacao;
	private String usuarioRegistro;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraRegistro;
	
	public GrupoHistoricoDto()
	{
	}
	
	public GrupoHistoricoDto(GrupoHistorico grupoHistorico)
	{
		this.id                = grupoHistorico.getId();
		this.idSituacao        = grupoHistorico.getSituacao().getId();
		this.descricaoSituacao = grupoHistorico.getSituacao().getDescricao();
		this.usuarioRegistro   = grupoHistorico.getUsuarioRegistro().getNome();
		this.dataHoraRegistro  = grupoHistorico.getDataHoraRegistro();
	}

	public Long getId()
	{
		return this.id;
	}

	public Long getidSituacao()
	{
		return this.idSituacao;
	}

	public String getDescricaoSituacao()
	{
		return this.descricaoSituacao;
	}

	public String getUsuarioRegistro()
	{
		return this.usuarioRegistro;
	}

	public LocalDateTime getDataHoraRegistro()
	{
		return this.dataHoraRegistro;
	}
	
	// converte os dados vindos da classe GrupoHistorico para a classe GrupoHistoricoDto
	public static List<GrupoHistoricoDto> converter(List<GrupoHistorico> grupoHistoricos)
	{
		List<GrupoHistoricoDto> grupoHistoricosDto = new ArrayList<>();
		
		for(GrupoHistorico gs : grupoHistoricos)
		{
			GrupoHistoricoDto grupoHistoricoDto = new GrupoHistoricoDto(gs);

			grupoHistoricosDto.add(grupoHistoricoDto);
		}

		return grupoHistoricosDto;
	}
}
