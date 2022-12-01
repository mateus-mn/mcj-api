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

import com.mcj.api.dto.PessoaDto;
import com.mcj.api.dto.TotalDto;
import com.mcj.api.form.PessoaForm;
import com.mcj.api.model.Pessoa;
import com.mcj.api.model.PessoaCelular;
import com.mcj.api.model.PessoaEmail;
import com.mcj.api.service.PessoaCelularService;
import com.mcj.api.service.PessoaEmailService;
import com.mcj.api.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	@Autowired
	private PessoaHistoricoController pessoaHistoricoController;
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private PessoaCelularService pessoaCelularService;
	@Autowired
	private PessoaEmailService pessoaEmailService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Pessoa";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<PessoaDto>> listar() {
		try {
			List<Pessoa> pessoas = pessoaService.listar();
			List<PessoaDto> pessoasDto = pessoaService.converterParaDto(pessoas);
			return ResponseEntity.ok(pessoasDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/listar/{id}")
	@CrossOrigin
	public ResponseEntity<List<PessoaDto>> listar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Pessoa pessoa = pessoaService.buscarPorId(id);

			if (pessoa != null) {
				// Obs.: o código 3 é referência para "acessado"
				pessoaHistoricoController.cadastrarHistorico(token, pessoa, Long.valueOf(3));
			}

			List<Pessoa> pessoas = new ArrayList<>();
			pessoas.add(pessoa);

			List<PessoaDto> pessoasDto = pessoaService.converterParaDto(pessoas);

			return ResponseEntity.ok(pessoasDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listarTotais")
	@CrossOrigin
	public ResponseEntity<TotalDto> listarTotais() {
		List<Pessoa> pessoas = pessoaService.listar();

		int totalAtivos = 0;
		int totalInativos = 0;
		for (Pessoa p : pessoas) {
			if (Boolean.TRUE.equals(p.getAtivo())) {
				totalAtivos++;
			} else {
				totalInativos++;
			}
		}

		TotalDto totais = new TotalDto(pessoas.size(), totalAtivos, totalInativos);
		return ResponseEntity.ok(totais);
	}

	@GetMapping("/listarAtivos")
	@CrossOrigin
	public ResponseEntity<List<PessoaDto>> listarAtivos() {
		List<Pessoa> pessoas = pessoaService.buscarSomenteAtivos();
		List<PessoaDto> pessoasDto = pessoaService.converterParaDto(pessoas);
		return ResponseEntity.ok(pessoasDto);
	}

	@GetMapping("/listarInativos")
	@CrossOrigin
	public ResponseEntity<List<PessoaDto>> listarInativos() {
		List<Pessoa> pessoas = pessoaService.buscarSomenteInativos();
		List<PessoaDto> pessoasDto = pessoaService.converterParaDto(pessoas);
		return ResponseEntity.ok(pessoasDto);
	}

	@PostMapping("/cadastrar")
	@Transactional
	@CrossOrigin
	public ResponseEntity<PessoaDto> cadastrar(@RequestBody PessoaForm form, UriComponentsBuilder uriComponentsBuilder,
			@RequestHeader("Authorization") String token) {
		try {
			Pessoa pessoa = pessoaService.cadastrar(form);
			PessoaCelular pessoaCelular = pessoaCelularService.cadastrar(pessoa, form.getCelular());
			PessoaEmail pessoaEmail = pessoaEmailService.cadastrar(pessoa, form.getEmail());

			// Obs.: o código 1 é referência para "cadastrado"
			pessoaHistoricoController.cadastrarHistorico(token, pessoa, Long.valueOf(1));

			URI uri = uriComponentsBuilder.path("/pessoa/listar/{id}").buildAndExpand(pessoa.getId()).toUri();
			return ResponseEntity.created(uri).body(new PessoaDto(pessoa, pessoaCelular, pessoaEmail));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/alterar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<PessoaDto> alterar(@RequestBody PessoaForm form, @PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		try {
			Pessoa pessoa = pessoaService.alterar(id, form);
			PessoaCelular pessoaCelular = pessoaCelularService.alterar(pessoa, form.getCelular());
			PessoaEmail pessoaEmail = pessoaEmailService.alterar(pessoa, form.getEmail());

			// Obs.: o código 2 é referência para "alterado"
			pessoaHistoricoController.cadastrarHistorico(token, pessoa, Long.valueOf(2));

			return ResponseEntity.ok(new PessoaDto(pessoa, pessoaCelular, pessoaEmail));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/desativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<PessoaDto> desativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Pessoa pessoa = pessoaService.desativar(id);
			PessoaCelular pessoaCelular = pessoaCelularService.buscarMaisRecente(id);
			PessoaEmail pessoaEmail = pessoaEmailService.buscarMaisRecente(id);

			// Obs.: o código 4 é referência para "desativado"
			pessoaHistoricoController.cadastrarHistorico(token, pessoa, Long.valueOf(4));

			return ResponseEntity.ok(new PessoaDto(pessoa, pessoaCelular, pessoaEmail));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/reativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<PessoaDto> reativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Pessoa pessoa = pessoaService.reativar(id);
			PessoaCelular pessoaCelular = pessoaCelularService.buscarMaisRecente(id);
			PessoaEmail pessoaEmail = pessoaEmailService.buscarMaisRecente(id);

			// Obs.: o código 5 é referência para "reativado"
			pessoaHistoricoController.cadastrarHistorico(token, pessoa, Long.valueOf(5));

			return ResponseEntity.ok(new PessoaDto(pessoa, pessoaCelular, pessoaEmail));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}