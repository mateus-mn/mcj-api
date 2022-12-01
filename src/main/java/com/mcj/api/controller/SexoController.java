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

import com.mcj.api.dto.SexoDto;
import com.mcj.api.model.Sexo;
import com.mcj.api.service.SexoService;

@RestController
@RequestMapping("/sexo")
public class SexoController {
	@Autowired
	private SexoService sexoService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Sexo";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<SexoDto>> listar() {
		try {
			List<Sexo> sexos = sexoService.listar();
			List<SexoDto> sexosDto = sexoService.converterParaDto(sexos);
			return ResponseEntity.ok(sexosDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listar/{id}")
	@CrossOrigin
	public ResponseEntity<List<SexoDto>> listar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Sexo sexo = sexoService.buscarPorId(id);

			List<Sexo> sexos = new ArrayList<>();
			sexos.add(sexo);

			List<SexoDto> sexosDto = sexoService.converterParaDto(sexos);

			return ResponseEntity.ok(sexosDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}