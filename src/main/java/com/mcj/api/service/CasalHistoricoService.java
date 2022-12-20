package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.CasalHistoricoDto;
import com.mcj.api.model.CasalHistorico;
import com.mcj.api.repository.CasalHistoricoRepository;

@Service
public class CasalHistoricoService {
	@Autowired
	private CasalHistoricoRepository casalHistoricoRepository;

	public List<CasalHistoricoDto> converterParaDto(List<CasalHistorico> casalHistoricos) {
		List<CasalHistoricoDto> casalHistoricosDto = new ArrayList<>();

		for (CasalHistorico ch : casalHistoricos) {
			CasalHistoricoDto casalHistoricoDto = new CasalHistoricoDto(ch);

			casalHistoricosDto.add(casalHistoricoDto);
		}

		return casalHistoricosDto;
	}

	public List<CasalHistorico> listar(Long idCasal) {
		return casalHistoricoRepository.buscarHistorico(idCasal);
	}

	public void cadastrar(CasalHistorico casalHistorico) {
		casalHistoricoRepository.save(casalHistorico);
	}
}
