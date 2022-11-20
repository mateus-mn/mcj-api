package com.mcj.api.form;

import com.mcj.api.model.Grupo;

public class GrupoForm
{
	private Long numero;
	private String nome;

	public Long getNumero()
	{
		return this.numero;
	}

	public String getNome()
	{
		return this.nome;
	}

	// converte os dados vindos da classe GrupoForm para a classe Grupo
	public Grupo converter()
	{
		return new Grupo(this.getNumero(), this.getNome());
	}
}