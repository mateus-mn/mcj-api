package com.mcj.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcj.api.model.UsuarioHistorico;

public class UsuarioHistoricoDto {
	private Long id;
	private Long idSituacao;
	private String descricaoSituacao;
	private String usuarioRegistro;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraRegistro;

	public UsuarioHistoricoDto() {
	}

	public UsuarioHistoricoDto(UsuarioHistorico usuarioHistorico) {
		this.id = usuarioHistorico.getId();
		this.idSituacao = usuarioHistorico.getSituacao().getId();
		this.descricaoSituacao = usuarioHistorico.getSituacao().getDescricao();
		this.usuarioRegistro = usuarioHistorico.getUsuarioRegistro().getNome();
		this.dataHoraRegistro = usuarioHistorico.getDataHoraRegistro();
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
