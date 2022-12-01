package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.GrupoHistoricoDto;
import com.mcj.api.model.GrupoHistorico;
import com.mcj.api.repository.GrupoHistoricoRepository;

@Service
public class GrupoHistoricoService {
	@Autowired
	private GrupoHistoricoRepository grupoHistoricoRepository;

	public List<GrupoHistoricoDto> converterParaDto(List<GrupoHistorico> grupoHistoricos) {
		List<GrupoHistoricoDto> grupoHistoricosDto = new ArrayList<>();

		for (GrupoHistorico gh : grupoHistoricos) {
			GrupoHistoricoDto grupoHistoricoDto = new GrupoHistoricoDto(gh);

			grupoHistoricosDto.add(grupoHistoricoDto);
		}

		return grupoHistoricosDto;
	}

	public List<GrupoHistorico> listar(Long idGrupo) {
		return grupoHistoricoRepository.buscarHistorico(idGrupo);
	}

	public void cadastrar(GrupoHistorico grupoHistorico) {
		grupoHistoricoRepository.save(grupoHistorico);
	}
}
