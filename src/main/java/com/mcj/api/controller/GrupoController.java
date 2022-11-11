package com.mcj.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcj.api.dto.GrupoDto;
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
}