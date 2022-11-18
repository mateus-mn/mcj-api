package com.mcj.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.model.Grupo;
import com.mcj.api.repository.GrupoRepository;

@Service
public class GrupoService
{
	@Autowired
	private GrupoRepository grupoRepository;
	
	public List<Grupo> listar ()
	{
		return grupoRepository.findAll();
	}

	public void cadastrar (Grupo grupo)
	{
		grupoRepository.save(grupo);
	}
}