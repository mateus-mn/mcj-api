package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.EstadoDto;
import com.mcj.api.model.Estado;
import com.mcj.api.repository.EstadoRepository;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository estadoRepository;

	public List<EstadoDto> converterParaDto(List<Estado> estados) {
		List<EstadoDto> estadosDto = new ArrayList<>();

		for (Estado e : estados) {
			EstadoDto estadoDto = new EstadoDto(e);

			estadosDto.add(estadoDto);
		}

		return estadosDto;
	}

	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	public Estado buscarPorId(Long id) {
		Optional<Estado> optionalEstado = estadoRepository.findById(id);

		if (optionalEstado.isPresent()) {
			return optionalEstado.get();
		}

		return null;
	}
}