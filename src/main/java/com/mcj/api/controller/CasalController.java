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

import com.mcj.api.dto.CasalDto;
import com.mcj.api.dto.TotalDto;
import com.mcj.api.form.CasalForm;
import com.mcj.api.model.Casal;
import com.mcj.api.model.CasalEndereco;
import com.mcj.api.model.Situacao;
import com.mcj.api.service.CasalEnderecoService;
import com.mcj.api.service.CasalService;

@RestController
@RequestMapping("/casal")
public class CasalController {
	@Autowired
	private CasalHistoricoController casalHistoricoController;
	@Autowired
	private CasalService casalService;
	@Autowired
	private CasalEnderecoService casalEnderecoService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Casal";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<CasalDto>> listar() {
		try {
			List<Casal> casais = casalService.listar();
			List<CasalDto> casaisDto = casalService.converterParaDto(casais);
			return ResponseEntity.ok(casaisDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/listar/{id}")
	@CrossOrigin
	public ResponseEntity<List<CasalDto>> listar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Casal casal = casalService.buscarPorId(id);

			if (casal != null) {
				// Obs.: o código 3 é referência para "acessado"
				Situacao situacao = new Situacao(Long.valueOf(3));
				casalHistoricoController.cadastrarHistorico(token, casal, situacao);
			}

			List<Casal> casais = new ArrayList<>();
			casais.add(casal);

			List<CasalDto> casaisDto = casalService.converterParaDto(casais);

			return ResponseEntity.ok(casaisDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listarTotais")
	@CrossOrigin
	public ResponseEntity<TotalDto> listarTotais() {
		List<Casal> casais = casalService.listar();

		int totalAtivos = 0;
		int totalInativos = 0;
		for (Casal c : casais) {
			if (Boolean.TRUE.equals(c.getAtivo())) {
				totalAtivos++;
			} else {
				totalInativos++;
			}
		}

		TotalDto totais = new TotalDto(casais.size(), totalAtivos, totalInativos);
		return ResponseEntity.ok(totais);
	}

	@GetMapping("/listarAtivos")
	@CrossOrigin
	public ResponseEntity<List<CasalDto>> listarAtivos() {
		List<Casal> casais = casalService.buscarSomenteAtivos();
		List<CasalDto> casaisDto = casalService.converterParaDto(casais);
		return ResponseEntity.ok(casaisDto);
	}

	@GetMapping("/listarInativos")
	@CrossOrigin
	public ResponseEntity<List<CasalDto>> listarInativos() {
		List<Casal> casais = casalService.buscarSomenteInativos();
		List<CasalDto> casaisDto = casalService.converterParaDto(casais);
		return ResponseEntity.ok(casaisDto);
	}

	@PostMapping("/cadastrar")
	@Transactional
	@CrossOrigin
	public ResponseEntity<CasalDto> cadastrar(@RequestBody CasalForm form, UriComponentsBuilder uriComponentsBuilder,
			@RequestHeader("Authorization") String token) {
		try {
			Casal casal = casalService.cadastrar(form);
			CasalEndereco endereco = casalEnderecoService.cadastrar(casal, form.getLogradouro(), form.getNumero(),
					form.getBairro());

			// Obs.: o código 1 é referência para "cadastrado"
			Situacao situacao = new Situacao(Long.valueOf(1));
			casalHistoricoController.cadastrarHistorico(token, casal, situacao);

			URI uri = uriComponentsBuilder.path("/casal/listar/{id}").buildAndExpand(casal.getId()).toUri();
			return ResponseEntity.created(uri).body(new CasalDto(casal, endereco));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/alterar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<CasalDto> alterar(@RequestBody CasalForm form, @PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		try {
			Casal casal = casalService.alterar(id, form);
			CasalEndereco endereco = casalEnderecoService.alterar(casal, form.getLogradouro(), form.getNumero(),
					form.getBairro());

			// Obs.: o código 2 é referência para "alterado"
			Situacao situacao = new Situacao(Long.valueOf(2));
			casalHistoricoController.cadastrarHistorico(token, casal, situacao);

			return ResponseEntity.ok(new CasalDto(casal, endereco));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/desativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<CasalDto> desativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Casal casal = casalService.desativar(id);
			CasalEndereco endereco = casalEnderecoService.buscarMaisRecente(id);

			// Obs.: o código 4 é referência para "desativado"
			Situacao situacao = new Situacao(Long.valueOf(4));
			casalHistoricoController.cadastrarHistorico(token, casal, situacao);

			return ResponseEntity.ok(new CasalDto(casal, endereco));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/reativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<CasalDto> reativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Casal casal = casalService.reativar(id);
			CasalEndereco endereco = casalEnderecoService.buscarMaisRecente(id);

			// Obs.: o código 5 é referência para "reativado"
			Situacao situacao = new Situacao(Long.valueOf(5));
			casalHistoricoController.cadastrarHistorico(token, casal, situacao);

			return ResponseEntity.ok(new CasalDto(casal, endereco));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}