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

import com.mcj.api.dto.BairroDto;
import com.mcj.api.dto.TotalDto;
import com.mcj.api.form.BairroForm;
import com.mcj.api.model.Bairro;
import com.mcj.api.model.Situacao;
import com.mcj.api.service.BairroService;

@RestController
@RequestMapping("/bairro")
public class BairroController {
	@Autowired
	private BairroHistoricoController bairroHistoricoController;
	@Autowired
	private BairroService bairroService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Bairro";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<BairroDto>> listar() {
		try {
			List<Bairro> bairros = bairroService.listar();
			List<BairroDto> bairrosDto = bairroService.converterParaDto(bairros);
			return ResponseEntity.ok(bairrosDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/listar/{id}")
	@CrossOrigin
	public ResponseEntity<List<BairroDto>> listar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Bairro bairro = bairroService.buscarPorId(id);

			if (bairro != null) {
				// Obs.: o código 3 é referência para "acessado"
				Situacao situacao = new Situacao(Long.valueOf(3));
				bairroHistoricoController.cadastrarHistorico(token, bairro, situacao);
			}

			List<Bairro> bairros = new ArrayList<>();
			bairros.add(bairro);

			List<BairroDto> bairrosDto = bairroService.converterParaDto(bairros);

			return ResponseEntity.ok(bairrosDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listarTotais")
	@CrossOrigin
	public ResponseEntity<TotalDto> listarTotais() {
		List<Bairro> bairros = bairroService.listar();

		int totalAtivos = 0;
		int totalInativos = 0;
		for (Bairro b : bairros) {
			if (Boolean.TRUE.equals(b.getAtivo())) {
				totalAtivos++;
			} else {
				totalInativos++;
			}
		}

		TotalDto totais = new TotalDto(bairros.size(), totalAtivos, totalInativos);
		return ResponseEntity.ok(totais);
	}

	@GetMapping("/listarAtivos")
	@CrossOrigin
	public ResponseEntity<List<BairroDto>> listarAtivos() {
		List<Bairro> bairros = bairroService.buscarSomenteAtivos();
		List<BairroDto> bairrosDto = bairroService.converterParaDto(bairros);
		return ResponseEntity.ok(bairrosDto);
	}

	@GetMapping("/listarInativos")
	@CrossOrigin
	public ResponseEntity<List<BairroDto>> listarInativos() {
		List<Bairro> bairros = bairroService.buscarSomenteInativos();
		List<BairroDto> bairrosDto = bairroService.converterParaDto(bairros);
		return ResponseEntity.ok(bairrosDto);
	}

	@PostMapping("/cadastrar")
	@Transactional
	@CrossOrigin
	public ResponseEntity<BairroDto> cadastrar(@RequestBody BairroForm form, UriComponentsBuilder uriComponentsBuilder,
			@RequestHeader("Authorization") String token) {
		try {
			Bairro bairro = bairroService.cadastrar(form);

			// Obs.: o código 1 é referência para "cadastrado"
			Situacao situacao = new Situacao(Long.valueOf(1));
			bairroHistoricoController.cadastrarHistorico(token, bairro, situacao);

			URI uri = uriComponentsBuilder.path("/bairro/listar/{id}").buildAndExpand(bairro.getId()).toUri();
			return ResponseEntity.created(uri).body(new BairroDto(bairro));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/alterar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<BairroDto> alterar(@RequestBody BairroForm form, @PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		try {
			Bairro bairro = bairroService.alterar(id, form);

			// Obs.: o código 2 é referência para "alterado"
			Situacao situacao = new Situacao(Long.valueOf(2));
			bairroHistoricoController.cadastrarHistorico(token, bairro, situacao);

			return ResponseEntity.ok(new BairroDto(bairro));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/desativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<BairroDto> desativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Bairro bairro = bairroService.desativar(id);

			// Obs.: o código 4 é referência para "desativado"
			Situacao situacao = new Situacao(Long.valueOf(4));
			bairroHistoricoController.cadastrarHistorico(token, bairro, situacao);

			return ResponseEntity.ok(new BairroDto(bairro));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/reativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<BairroDto> reativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Bairro bairro = bairroService.reativar(id);

			// Obs.: o código 5 é referência para "reativado"
			Situacao situacao = new Situacao(Long.valueOf(5));
			bairroHistoricoController.cadastrarHistorico(token, bairro, situacao);

			return ResponseEntity.ok(new BairroDto(bairro));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}