package com.mcj.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcj.api.model.Pessoa;
import com.mcj.api.model.PessoaHistorico;
import com.mcj.api.model.Situacao;
import com.mcj.api.model.Usuario;
import com.mcj.api.dto.PessoaHistoricoDto;
import com.mcj.api.service.PessoaHistoricoService;
import com.mcj.api.service.TokenService;

@RestController
@RequestMapping("/pessoaHistorico")
public class PessoaHistoricoController {
	@Autowired
	private PessoaHistoricoService pessoaHistoricoService;
	@Autowired
	private TokenService tokenService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Pessoa Histórico";
	}

	@GetMapping("/listar/{idPessoa}")
	@CrossOrigin
	public ResponseEntity<List<PessoaHistoricoDto>> buscarHistorico(@PathVariable Long idPessoa) {
		List<PessoaHistorico> pessoaHistorico = pessoaHistoricoService.listar(idPessoa);
		List<PessoaHistoricoDto> pessoaHistoricosDto = pessoaHistoricoService.converterParaDto(pessoaHistorico);
		return ResponseEntity.ok(pessoaHistoricosDto);
	}

	public void cadastrarHistorico(String token, Pessoa pessoa, Long idSituacao) {
		Usuario usuario = tokenService.getCredenciaisUsuario(token);

		PessoaHistorico pessoaHistorico = new PessoaHistorico(pessoa, new Situacao(idSituacao), usuario);
		pessoaHistoricoService.cadastrar(pessoaHistorico);
	}
}