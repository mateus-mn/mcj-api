package com.mcj.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.mcj.api.model.Grupo;

public class GrupoDto
{
	// PARÂMETROS
	private Long id;
	
	private Long numero;
	
	private String nome;
	
	private Boolean ativo;
	// FIM DOS PARÂMETROS
	
	// CONSTRUTORES
	public GrupoDto()
	{
	}
	
	public GrupoDto(Grupo grupo)
	{
		this.id     = grupo.getId();
		this.numero = grupo.getNumero();
		this.nome   = grupo.getNome();
		this.ativo  = grupo.getAtivo();
	}
	// FIM DOS CONSTRUTORES
	
	// GETTERS
	public Long getId()
	{
		return this.id;
	}

	public Long getNumero()
	{
		return this.numero;
	}

	public String getNome()
	{
		return this.nome;
	}

	public Boolean getAtivo()
	{
		return this.ativo;
	}
	// FIM DOS GETTERS

	// OUTROS MÉTODOS
	// converte os dados vindos da classe Grupo para a classe GrupoDto
	public static List<GrupoDto> converter(List<Grupo> grupos)
	{
		List<GrupoDto> gruposDto = new ArrayList<>();
		
		for(Grupo g : grupos)
		{
			GrupoDto grupoDto = new GrupoDto(g);

			gruposDto.add(grupoDto);
		}

		return gruposDto;
	}
	// FIM DOS OUTROS MÉTODOS
}
