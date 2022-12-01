package com.mcj.api.dto;

import com.mcj.api.model.Usuario;

public class UsuarioDto {
	private Long id;
	private String nome;
	private String login;
	private String senha;
	private Boolean ativo;

	public UsuarioDto() {
	}

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.login = usuario.getLogin();
		this.senha = usuario.getSenha();
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

	public String getSenha() {
		return this.senha;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}
}
