package com.mcj.api.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mcj.api.dto.GrupoDto;
import com.mcj.api.form.GrupoForm;
import com.mcj.api.model.Grupo;
import com.mcj.api.service.GrupoService;

@RestController
@RequestMapping("/grupo")
public class GrupoController
{
	@Autowired
	private GrupoService grupoService;

	@GetMapping(value = {"", "/"})
	@CrossOrigin
	public String index()
	{
		return "Bem vindo à Entidade Grupo";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<GrupoDto>> listar()
	{
		List<Grupo> grupos = grupoService.listar();
		return ResponseEntity.ok(GrupoDto.converter(grupos));
	}

	@PostMapping("/cadastrar")
	@Transactional
	@CrossOrigin
	public ResponseEntity<GrupoDto> cadastrar(@RequestBody GrupoForm form, UriComponentsBuilder uriComponentsBuilder)
	{
		try
		{
			Grupo grupo = form.converter();
			grupoService.cadastrar(grupo);
			
			URI uri = uriComponentsBuilder.path("/grupo/listar/{id}").buildAndExpand(grupo.getId()).toUri();
			return ResponseEntity.created(uri).body(new GrupoDto(grupo));
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/alterar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<GrupoDto> alterar(@RequestBody GrupoForm form, @PathVariable Long id)
	{
		try
		{
			Grupo grupo = grupoService.alterar(id, form);
			return ResponseEntity.ok(new GrupoDto(grupo));
		}
		catch(Exception e)
		{
			return ResponseEntity.badRequest().build();
		}
	}
}