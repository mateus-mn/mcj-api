package com.mcj.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcj.api.model.CidadeHistorico;

public class CidadeHistoricoDto {
	private Long id;
	private Long idSituacao;
	private String descricaoSituacao;
	private String usuarioRegistro;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraRegistro;

	public CidadeHistoricoDto() {
	}

	public CidadeHistoricoDto(CidadeHistorico cidadeHistorico) {
		this.id = cidadeHistorico.getId();
		this.idSituacao = cidadeHistorico.getSituacao().getId();
		this.descricaoSituacao = cidadeHistorico.getSituacao().getDescricao();
		this.usuarioRegistro = cidadeHistorico.getUsuarioRegistro().getNome();
		this.dataHoraRegistro = cidadeHistorico.getDataHoraRegistro();
	}

	public Long getId() {
		return this.id;
	}

	public Long getidSituacao() {
		return this.idSituacao;
	}

	public String getDescricaoSituacao() {
		return this.descricaoSituacao;
	}

	public String getUsuarioRegistro() {
		return this.usuarioRegistro;
	}

	public LocalDateTime getDataHoraRegistro() {
		return this.dataHoraRegistro;
	}
}
