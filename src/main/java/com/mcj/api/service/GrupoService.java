package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.form.GrupoForm;
import com.mcj.api.model.Grupo;
import com.mcj.api.repository.GrupoRepository;

@Service
public class GrupoService
{
	@Autowired
	private GrupoRepository grupoRepository;
	
	public List<Grupo> listar ()
	{
		return grupoRepository.findAll();
	}

	public List<Grupo> buscarPorId(Long id)
	{
		Optional<Grupo> optionalGrupo = grupoRepository.findById(id);

		List<Grupo> grupos = new ArrayList<>();

		if(optionalGrupo.isPresent())
		{
			grupos.add(optionalGrupo.get());
		}

		return grupos;
	}

	public void cadastrar (Grupo grupo)
	{
		grupoRepository.save(grupo);
	}

	public Grupo alterar(Long id, GrupoForm form)
	{
		Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
		if(optionalGrupo.isPresent())
		{
			Grupo grupo = optionalGrupo.get();

			grupo.setNumero(form.getNumero());
			grupo.setNome(form.getNome());
			
			return grupo;
		}

		return null;
	}
}