package com.mcj.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.model.GrupoHistorico;
import com.mcj.api.repository.GrupoHistoricoRepository;

@Service
public class GrupoHistoricoService
{
	@Autowired
	private GrupoHistoricoRepository grupoHistoricoRepository;

	public List<GrupoHistorico> listar (Long idGrupo)
	{
		return grupoHistoricoRepository.buscarHistorico(idGrupo);
	}
	
	public void cadastrar (GrupoHistorico grupoHistorico)
	{
		grupoHistoricoRepository.save(grupoHistorico);
	}
}
