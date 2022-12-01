package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.PessoaHistoricoDto;
import com.mcj.api.model.PessoaHistorico;
import com.mcj.api.repository.PessoaHistoricoRepository;

@Service
public class PessoaHistoricoService {
	@Autowired
	private PessoaHistoricoRepository pessoaHistoricoRepository;

	public List<PessoaHistoricoDto> converterParaDto(List<PessoaHistorico> pessoaHistoricos) {
		List<PessoaHistoricoDto> pessoaHistoricosDto = new ArrayList<>();

		for (PessoaHistorico ph : pessoaHistoricos) {
			PessoaHistoricoDto pessoaHistoricoDto = new PessoaHistoricoDto(ph);

			pessoaHistoricosDto.add(pessoaHistoricoDto);
		}

		return pessoaHistoricosDto;
	}

	public List<PessoaHistorico> listar(Long idPessoa) {
		return pessoaHistoricoRepository.buscarHistorico(idPessoa);
	}

	public void cadastrar(PessoaHistorico pessoaHistorico) {
		pessoaHistoricoRepository.save(pessoaHistorico);
	}
}
