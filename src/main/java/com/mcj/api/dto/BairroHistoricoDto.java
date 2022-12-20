package com.mcj.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcj.api.model.BairroHistorico;

public class BairroHistoricoDto {
	private Long id;
	private Long idSituacao;
	private String descricaoSituacao;
	private String usuarioRegistro;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraRegistro;

	public BairroHistoricoDto() {
	}

	public BairroHistoricoDto(BairroHistorico bairroHistorico) {
		this.id = bairroHistorico.getId();
		this.idSituacao = bairroHistorico.getSituacao().getId();
		this.descricaoSituacao = bairroHistorico.getSituacao().getDescricao();
		this.usuarioRegistro = bairroHistorico.getUsuarioRegistro().getNome();
		this.dataHoraRegistro = bairroHistorico.getDataHoraRegistro();
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
