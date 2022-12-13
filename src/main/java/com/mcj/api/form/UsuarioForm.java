package com.mcj.api.form;

import java.util.List;

public class UsuarioForm {
	private String nome;
	private String login;
	private String senha;
	private List<Long> perfis;

	public String getNome() {
		return this.nome;
	}

	public String getLogin() {
		return this.login;
	}

	public String getSenha() {
		return this.senha;
	}

	public List<Long> getPerfis() {
		return this.perfis;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}