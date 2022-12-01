package com.mcj.api.form;

public class PessoaForm {
	private String nome;
	private Long sexo;
	private String dataNascimento;
	private String celular;
	private String email;

	public String getNome() {
		return this.nome;
	}

	public Long getSexo() {
		return this.sexo;
	}

	public String getDataNascimento() {
		return this.dataNascimento;
	}

	public String getCelular() {
		return this.celular;
	}

	public String getEmail() {
		return this.email;
	}
}