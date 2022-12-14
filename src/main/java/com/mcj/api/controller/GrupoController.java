package com.mcj.api.controller;

import java.net.URI;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mcj.api.dto.GrupoDto;
import com.mcj.api.dto.TotalDto;
import com.mcj.api.form.GrupoForm;
import com.mcj.api.model.Grupo;
import com.mcj.api.model.Situacao;
import com.mcj.api.service.GrupoService;

@RestController
@RequestMapping("/grupo")
public class GrupoController {
	@Autowired
	private GrupoHistoricoController grupoHistoricoController;
	@Autowired
	private GrupoService grupoService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Grupo";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<GrupoDto>> listar() {
		try {
			List<Grupo> grupos = grupoService.listar();
			List<GrupoDto> gruposDto = grupoService.converterParaDto(grupos);
			return ResponseEntity.ok(gruposDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listar/{id}")
	@CrossOrigin
	public ResponseEntity<List<GrupoDto>> listar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Grupo grupo = grupoService.buscarPorId(id);

			if (grupo != null) {
				// Obs.: o código 3 é referência para "acessado"
				Situacao situacao = new Situacao(Long.valueOf(3));
				grupoHistoricoController.cadastrarHistorico(token, grupo, situacao);
			}

			List<Grupo> grupos = new ArrayList<>();
			grupos.add(grupo);

			List<GrupoDto> gruposDto = grupoService.converterParaDto(grupos);

			return ResponseEntity.ok(gruposDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listarTotais")
	@CrossOrigin
	public ResponseEntity<TotalDto> listarTotais() {
		List<Grupo> grupos = grupoService.listar();

		int totalAtivos = 0;
		int totalInativos = 0;
		for (Grupo g : grupos) {
			if (Boolean.TRUE.equals(g.getAtivo())) {
				totalAtivos++;
			} else {
				totalInativos++;
			}
		}

		TotalDto totais = new TotalDto(grupos.size(), totalAtivos, totalInativos);
		return ResponseEntity.ok(totais);
	}

	@GetMapping("/listarAtivos")
	@CrossOrigin
	public ResponseEntity<List<GrupoDto>> listarAtivos() {
		List<Grupo> grupos = grupoService.buscarSomenteAtivos();
		List<GrupoDto> gruposDto = grupoService.converterParaDto(grupos);
		return ResponseEntity.ok(gruposDto);
	}

	@GetMapping("/listarInativos")
	@CrossOrigin
	public ResponseEntity<List<GrupoDto>> listarInativos() {
		List<Grupo> grupos = grupoService.buscarSomenteInativos();
		List<GrupoDto> gruposDto = grupoService.converterParaDto(grupos);
		return ResponseEntity.ok(gruposDto);
	}

	@PostMapping("/cadastrar")
	@Transactional
	@CrossOrigin
	public ResponseEntity<GrupoDto> cadastrar(@RequestBody GrupoForm form, UriComponentsBuilder uriComponentsBuilder,
			@RequestHeader("Authorization") String token) {
		try {
			Grupo grupo = grupoService.cadastrar(form);

			// Obs.: o código 1 é referência para "cadastrado"
			Situacao situacao = new Situacao(Long.valueOf(1));
			grupoHistoricoController.cadastrarHistorico(token, grupo, situacao);

			URI uri = uriComponentsBuilder.path("/grupo/listar/{id}").buildAndExpand(grupo.getId()).toUri();
			return ResponseEntity.created(uri).body(new GrupoDto(grupo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/alterar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<GrupoDto> alterar(@RequestBody GrupoForm form, @PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		try {
			Grupo grupo = grupoService.alterar(id, form);

			// Obs.: o código 2 é referência para "alterado"
			Situacao situacao = new Situacao(Long.valueOf(2));
			grupoHistoricoController.cadastrarHistorico(token, grupo, situacao);

			return ResponseEntity.ok(new GrupoDto(grupo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/desativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<GrupoDto> desativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Grupo grupo = grupoService.desativar(id);

			// Obs.: o código 4 é referência para "desativado"
			Situacao situacao = new Situacao(Long.valueOf(4));
			grupoHistoricoController.cadastrarHistorico(token, grupo, situacao);

			return ResponseEntity.ok(new GrupoDto(grupo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/reativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<GrupoDto> reativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Grupo grupo = grupoService.reativar(id);

			// Obs.: o código 5 é referência para "reativado"
			Situacao situacao = new Situacao(Long.valueOf(5));
			grupoHistoricoController.cadastrarHistorico(token, grupo, situacao);

			return ResponseEntity.ok(new GrupoDto(grupo));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}