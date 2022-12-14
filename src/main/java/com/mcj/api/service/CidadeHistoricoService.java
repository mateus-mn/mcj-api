package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.CidadeHistoricoDto;
import com.mcj.api.model.CidadeHistorico;
import com.mcj.api.repository.CidadeHistoricoRepository;

@Service
public class CidadeHistoricoService {
	@Autowired
	private CidadeHistoricoRepository cidadeHistoricoRepository;

	public List<CidadeHistoricoDto> converterParaDto(List<CidadeHistorico> cidadeHistoricos) {
		List<CidadeHistoricoDto> cidadeHistoricosDto = new ArrayList<>();

		for (CidadeHistorico ch : cidadeHistoricos) {
			CidadeHistoricoDto cidadeHistoricoDto = new CidadeHistoricoDto(ch);

			cidadeHistoricosDto.add(cidadeHistoricoDto);
		}

		return cidadeHistoricosDto;
	}

	public List<CidadeHistorico> listar(Long idCidade) {
		return cidadeHistoricoRepository.buscarHistorico(idCidade);
	}

	public void cadastrar(CidadeHistorico cidadeHistorico) {
		cidadeHistoricoRepository.save(cidadeHistorico);
	}
}
