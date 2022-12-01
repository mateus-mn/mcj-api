package com.mcj.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcj.api.model.PessoaHistorico;

public class PessoaHistoricoDto {
	private Long id;
	private Long idSituacao;
	private String descricaoSituacao;
	private String usuarioRegistro;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraRegistro;

	public PessoaHistoricoDto() {
	}

	public PessoaHistoricoDto(PessoaHistorico pessoaHistorico) {
		this.id = pessoaHistorico.getId();
		this.idSituacao = pessoaHistorico.getSituacao().getId();
		this.descricaoSituacao = pessoaHistorico.getSituacao().getDescricao();
		this.usuarioRegistro = pessoaHistorico.getUsuarioRegistro().getNome();
		this.dataHoraRegistro = pessoaHistorico.getDataHoraRegistro();
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
