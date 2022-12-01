package com.mcj.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mcj.api.model.Pessoa;
import com.mcj.api.model.PessoaCelular;
import com.mcj.api.model.PessoaEmail;

public class PessoaDto {
	private Long id;

	private String nome;

	private Long idSexo;

	private String descricaoSexo;

	private Character siglaSexo;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	private String celular;

	private String email;

	private Boolean ativo;

	public PessoaDto() {
	}

	public PessoaDto(Pessoa pessoa, PessoaCelular celular, PessoaEmail email) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.idSexo = pessoa.getSexo().getId();
		this.descricaoSexo = pessoa.getSexo().getDescricao();
		this.siglaSexo = pessoa.getSexo().getSigla();
		this.dataNascimento = pessoa.getDataNascimento();

		if (celular != null) {
			this.celular = celular.getCelular();
		}

		if (email != null) {
			this.email = email.getEmail();
		}

		this.ativo = pessoa.getAtivo();
	}

	public Long getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public Long getidSexo() {
		return this.idSexo;
	}

	public String getDescricaoSexo() {
		return this.descricaoSexo;
	}

	public Character getSiglaSexo() {
		return this.siglaSexo;
	}

	public LocalDate getDataNascimento() {
		return this.dataNascimento;
	}

	public String getCelular() {
		return this.celular;
	}

	public String getEmail() {
		return this.email;
	}

	public Boolean getAtivo() {
		return this.ativo;
	}
}
