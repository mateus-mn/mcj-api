package com.mcj.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcj.api.model.CasalHistorico;

public class CasalHistoricoDto {
	private Long id;
	private Long idSituacao;
	private String descricaoSituacao;
	private String usuarioRegistro;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraRegistro;

	public CasalHistoricoDto() {
	}

	public CasalHistoricoDto(CasalHistorico casalHistorico) {
		this.id = casalHistorico.getId();
		this.idSituacao = casalHistorico.getSituacao().getId();
		this.descricaoSituacao = casalHistorico.getSituacao().getDescricao();
		this.usuarioRegistro = casalHistorico.getUsuarioRegistro().getNome();
		this.dataHoraRegistro = casalHistorico.getDataHoraRegistro();
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
