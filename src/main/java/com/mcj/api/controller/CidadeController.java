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

import com.mcj.api.dto.CidadeDto;
import com.mcj.api.dto.TotalDto;
import com.mcj.api.form.CidadeForm;
import com.mcj.api.model.Cidade;
import com.mcj.api.model.Situacao;
import com.mcj.api.service.CidadeService;

@RestController
@RequestMapping("/cidade")
public class CidadeController {
	@Autowired
	private CidadeHistoricoController cidadeHistoricoController;
	@Autowired
	private CidadeService cidadeService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Cidade";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<CidadeDto>> listar() {
		try {
			List<Cidade> cidades = cidadeService.listar();
			List<CidadeDto> cidadesDto = cidadeService.converterParaDto(cidades);
			return ResponseEntity.ok(cidadesDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listar/{id}")
	@CrossOrigin
	public ResponseEntity<List<CidadeDto>> listar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Cidade cidade = cidadeService.buscarPorId(id);

			if (cidade != null) {
				// Obs.: o código 3 é referência para "acessado"
				Situacao situacao = new Situacao(Long.valueOf(3));
				cidadeHistoricoController.cadastrarHistorico(token, cidade, situacao);
			}

			List<Cidade> cidades = new ArrayList<>();
			cidades.add(cidade);

			List<CidadeDto> cidadesDto = cidadeService.converterParaDto(cidades);

			return ResponseEntity.ok(cidadesDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listarTotais")
	@CrossOrigin
	public ResponseEntity<TotalDto> listarTotais() {
		List<Cidade> cidades = cidadeService.listar();

		int totalAtivos = 0;
		int totalInativos = 0;
		for (Cidade c : cidades) {
			if (Boolean.TRUE.equals(c.getAtivo())) {
				totalAtivos++;
			} else {
				totalInativos++;
			}
		}

		TotalDto totais = new TotalDto(cidades.size(), totalAtivos, totalInativos);
		return ResponseEntity.ok(totais);
	}

	@GetMapping("/listarAtivos")
	@CrossOrigin
	public ResponseEntity<List<CidadeDto>> listarAtivos() {
		List<Cidade> cidades = cidadeService.buscarSomenteAtivos();
		List<CidadeDto> cidadesDto = cidadeService.converterParaDto(cidades);
		return ResponseEntity.ok(cidadesDto);
	}

	@GetMapping("/listarInativos")
	@CrossOrigin
	public ResponseEntity<List<CidadeDto>> listarInativos() {
		List<Cidade> cidades = cidadeService.buscarSomenteInativos();
		List<CidadeDto> cidadesDto = cidadeService.converterParaDto(cidades);
		return ResponseEntity.ok(cidadesDto);
	}

	@PostMapping("/cadastrar")
	@Transactional
	@CrossOrigin
	public ResponseEntity<CidadeDto> cadastrar(@RequestBody CidadeForm form, UriComponentsBuilder uriComponentsBuilder,
			@RequestHeader("Authorization") String token) {
		try {
			Cidade cidade = cidadeService.cadastrar(form);

			// Obs.: o código 1 é referência para "cadastrado"
			Situacao situacao = new Situacao(Long.valueOf(1));
			cidadeHistoricoController.cadastrarHistorico(token, cidade, situacao);

			URI uri = uriComponentsBuilder.path("/cidade/listar/{id}").buildAndExpand(cidade.getId()).toUri();
			return ResponseEntity.created(uri).body(new CidadeDto(cidade));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/alterar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<CidadeDto> alterar(@RequestBody CidadeForm form, @PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		try {
			Cidade cidade = cidadeService.alterar(id, form);

			// Obs.: o código 2 é referência para "alterado"
			Situacao situacao = new Situacao(Long.valueOf(2));
			cidadeHistoricoController.cadastrarHistorico(token, cidade, situacao);

			return ResponseEntity.ok(new CidadeDto(cidade));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/desativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<CidadeDto> desativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Cidade cidade = cidadeService.desativar(id);

			// Obs.: o código 4 é referência para "desativado"
			Situacao situacao = new Situacao(Long.valueOf(4));
			cidadeHistoricoController.cadastrarHistorico(token, cidade, situacao);

			return ResponseEntity.ok(new CidadeDto(cidade));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/reativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<CidadeDto> reativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Cidade cidade = cidadeService.reativar(id);

			// Obs.: o código 5 é referência para "reativado"
			Situacao situacao = new Situacao(Long.valueOf(5));
			cidadeHistoricoController.cadastrarHistorico(token, cidade, situacao);

			return ResponseEntity.ok(new CidadeDto(cidade));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}