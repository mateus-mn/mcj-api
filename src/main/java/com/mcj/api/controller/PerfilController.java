package com.mcj.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcj.api.dto.PerfilDto;
import com.mcj.api.model.Perfil;
import com.mcj.api.service.PerfilService;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
	@Autowired
	private PerfilService perfilService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Perfil";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<PerfilDto>> listar() {
		try {
			List<Perfil> perfis = perfilService.listar();
			List<PerfilDto> perfisDto = PerfilService.converterParaDto(perfis);
			return ResponseEntity.ok(perfisDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listar/{id}")
	@CrossOrigin
	public ResponseEntity<List<PerfilDto>> listar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Perfil perfil = perfilService.buscarPorId(id);

			List<Perfil> perfis = new ArrayList<>();
			perfis.add(perfil);

			List<PerfilDto> perfisDto = PerfilService.converterParaDto(perfis);

			return ResponseEntity.ok(perfisDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}