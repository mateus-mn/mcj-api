package com.mcj.api.form;

import com.mcj.api.model.Grupo;

public class GrupoForm
{
	// PARÂMETROS
	private Long numero;
	private String nome;
	// FIM DOS PARÂMETROS
	
	// GETTERS
	public Long getNumero()
	{
		return this.numero;
	}

	public String getNome()
	{
		return this.nome;
	}
	// FIM DOS GETTERS

	// OUTROS MÉTODOS
	// converte os dados vindos da classe GrupoForm para a classe Grupo
	public Grupo converter()
	{
		return new Grupo(this.getNumero(), this.getNome());
	}
}
