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

import com.mcj.api.dto.EstadoDto;
import com.mcj.api.model.Estado;
import com.mcj.api.service.EstadoService;

@RestController
@RequestMapping("/estado")
public class EstadoController {
	@Autowired
	private EstadoService estadoService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Estado";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<EstadoDto>> listar() {
		try {
			List<Estado> estados = estadoService.listar();
			List<EstadoDto> estadosDto = estadoService.converterParaDto(estados);
			return ResponseEntity.ok(estadosDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listar/{id}")
	@CrossOrigin
	public ResponseEntity<List<EstadoDto>> listar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Estado estado = estadoService.buscarPorId(id);

			List<Estado> estados = new ArrayList<>();
			estados.add(estado);

			List<EstadoDto> estadosDto = estadoService.converterParaDto(estados);

			return ResponseEntity.ok(estadosDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}