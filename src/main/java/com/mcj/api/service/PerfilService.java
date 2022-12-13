package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.PerfilDto;
import com.mcj.api.model.Perfil;
import com.mcj.api.repository.PerfilRepository;

@Service
public class PerfilService {
	@Autowired
	private PerfilRepository perfilRepository;

	public static List<PerfilDto> converterParaDto(List<Perfil> perfis) {
		List<PerfilDto> perfisDto = new ArrayList<>();

		for (Perfil p : perfis) {
			PerfilDto perfilDto = new PerfilDto(p);

			perfisDto.add(perfilDto);
		}

		return perfisDto;
	}

	public List<Perfil> listar() {
		return perfilRepository.findAll();
	}

	public Perfil buscarPorId(Long id) {
		Optional<Perfil> optionalPerfil = perfilRepository.findById(id);

		if (optionalPerfil.isPresent()) {
			return optionalPerfil.get();
		}

		return null;
	}
}