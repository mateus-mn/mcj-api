package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.GrupoDto;
import com.mcj.api.form.GrupoForm;
import com.mcj.api.model.Grupo;
import com.mcj.api.repository.GrupoRepository;

@Service
public class GrupoService {
	@Autowired
	private GrupoRepository grupoRepository;

	public Grupo converter(GrupoForm grupoForm) {
		return new Grupo(grupoForm.getNumero(), grupoForm.getNome());
	}

	public List<GrupoDto> converterParaDto(List<Grupo> grupos) {
		List<GrupoDto> gruposDto = new ArrayList<>();

		for (Grupo g : grupos) {
			GrupoDto grupoDto = new GrupoDto(g);

			gruposDto.add(grupoDto);
		}

		return gruposDto;
	}

	public List<Grupo> listar() {
		return grupoRepository.findAll();
	}

	public Grupo buscarPorId(Long id) {
		Optional<Grupo> optionalGrupo = grupoRepository.findById(id);

		if (optionalGrupo.isPresent()) {
			return optionalGrupo.get();
		}

		return null;
	}

	public List<Grupo> buscarSomenteAtivos() {
		return grupoRepository.buscarSomenteAtivos();
	}

	public List<Grupo> buscarSomenteInativos() {
		return grupoRepository.buscarSomenteInativos();
	}

	public Grupo cadastrar(GrupoForm grupoForm) {
		Grupo grupo = converter(grupoForm);
		grupoRepository.save(grupo);

		return grupo;
	}

	public Grupo alterar(Long id, GrupoForm form) {
		Grupo grupo = buscarPorId(id);

		grupo.setNumero(form.getNumero());
		grupo.setNome(form.getNome());

		return grupo;
	}

	public Grupo desativar(Long id) {
		Grupo grupo = buscarPorId(id);
		grupo.setAtivo(false);
		return grupo;
	}

	public Grupo reativar(Long id) {
		Grupo grupo = buscarPorId(id);
		grupo.setAtivo(true);
		return grupo;
	}
}