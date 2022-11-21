package com.mcj.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.model.GrupoHistorico;
import com.mcj.api.repository.GrupoHistoricoRepository;

@Service
public class GrupoHistoricoService
{
	@Autowired
	private GrupoHistoricoRepository grupoHistoricoRepository;
	
	public void cadastrar (GrupoHistorico grupoHistorico)
	{
		grupoHistoricoRepository.save(grupoHistorico);
	}
}
