package com.mcj.api.dto;

import java.util.List;

import com.mcj.api.model.Usuario;
import com.mcj.api.service.PerfilService;

public class UsuarioDto {
	private Long id;
	private String nome;
	private String login;
	private List<PerfilDto> perfis;
	private Boolean ativo;

	public UsuarioDto() {
	}

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.login = usuario.getLogin();
		this.perfis = PerfilService.converterParaDto(usuario.getPerfis());
		this.ativo = usuario.getAtivo();
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public String getLogin() {
		return this.login;
	}

	public List<PerfilDto> getPerfis() {
		return this.perfis;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}
}
