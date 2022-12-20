package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.BairroHistoricoDto;
import com.mcj.api.model.BairroHistorico;
import com.mcj.api.repository.BairroHistoricoRepository;

@Service
public class BairroHistoricoService {
	@Autowired
	private BairroHistoricoRepository bairroHistoricoRepository;

	public List<BairroHistoricoDto> converterParaDto(List<BairroHistorico> bairroHistoricos) {
		List<BairroHistoricoDto> bairroHistoricosDto = new ArrayList<>();

		for (BairroHistorico bh : bairroHistoricos) {
			BairroHistoricoDto bairroHistoricoDto = new BairroHistoricoDto(bh);

			bairroHistoricosDto.add(bairroHistoricoDto);
		}

		return bairroHistoricosDto;
	}

	public List<BairroHistorico> listar(Long idBairro) {
		return bairroHistoricoRepository.buscarHistorico(idBairro);
	}

	public void cadastrar(BairroHistorico bairroHistorico) {
		bairroHistoricoRepository.save(bairroHistorico);
	}
}
