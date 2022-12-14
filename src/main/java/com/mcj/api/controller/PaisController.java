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

import com.mcj.api.dto.PaisDto;
import com.mcj.api.model.Pais;
import com.mcj.api.service.PaisService;

@RestController
@RequestMapping("/pais")
public class PaisController {
	@Autowired
	private PaisService paisService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade País";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<PaisDto>> listar() {
		try {
			List<Pais> paises = paisService.listar();
			List<PaisDto> paisesDto = paisService.converterParaDto(paises);
			return ResponseEntity.ok(paisesDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listar/{id}")
	@CrossOrigin
	public ResponseEntity<List<PaisDto>> listar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Pais pais = paisService.buscarPorId(id);

			List<Pais> paises = new ArrayList<>();
			paises.add(pais);

			List<PaisDto> paisesDto = paisService.converterParaDto(paises);

			return ResponseEntity.ok(paisesDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}