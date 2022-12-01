package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.SexoDto;
import com.mcj.api.model.Sexo;
import com.mcj.api.repository.SexoRepository;

@Service
public class SexoService {
	@Autowired
	private SexoRepository sexoRepository;

	public List<SexoDto> converterParaDto(List<Sexo> sexos) {
		List<SexoDto> sexosDto = new ArrayList<>();

		for (Sexo s : sexos) {
			SexoDto sexoDto = new SexoDto(s);

			sexosDto.add(sexoDto);
		}

		return sexosDto;
	}

	public List<Sexo> listar() {
		return sexoRepository.findAll();
	}

	public Sexo buscarPorId(Long id) {
		Optional<Sexo> optionalSexo = sexoRepository.findById(id);

		if (optionalSexo.isPresent()) {
			return optionalSexo.get();
		}

		return null;
	}
}