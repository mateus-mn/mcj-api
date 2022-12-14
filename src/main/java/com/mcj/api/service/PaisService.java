package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.PaisDto;
import com.mcj.api.model.Pais;
import com.mcj.api.repository.PaisRepository;

@Service
public class PaisService {
	@Autowired
	private PaisRepository paisRepository;

	public List<PaisDto> converterParaDto(List<Pais> paises) {
		List<PaisDto> paisesDto = new ArrayList<>();

		for (Pais p : paises) {
			PaisDto paisDto = new PaisDto(p);

			paisesDto.add(paisDto);
		}

		return paisesDto;
	}

	public List<Pais> listar() {
		return paisRepository.findAll();
	}

	public Pais buscarPorId(Long id) {
		Optional<Pais> optionalPais = paisRepository.findById(id);

		if (optionalPais.isPresent()) {
			return optionalPais.get();
		}

		return null;
	}
}