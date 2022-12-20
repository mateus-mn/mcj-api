package com.mcj.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcj.api.model.Cidade;
import com.mcj.api.model.CidadeHistorico;
import com.mcj.api.model.Situacao;
import com.mcj.api.model.Usuario;
import com.mcj.api.dto.CidadeHistoricoDto;
import com.mcj.api.service.CidadeHistoricoService;
import com.mcj.api.service.TokenService;

@RestController
@RequestMapping("/cidadeHistorico")
public class CidadeHistoricoController {
	@Autowired
	private CidadeHistoricoService cidadeHistoricoService;
	@Autowired
	private TokenService tokenService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Cidade Histórico";
	}

	@GetMapping("/listar/{idCidade}")
	@CrossOrigin
	public ResponseEntity<List<CidadeHistoricoDto>> buscarHistorico(@PathVariable Long idCidade) {
		List<CidadeHistorico> cidadeHistorico = cidadeHistoricoService.listar(idCidade);
		List<CidadeHistoricoDto> cidadeHistoricosDto = cidadeHistoricoService.converterParaDto(cidadeHistorico);
		return ResponseEntity.ok(cidadeHistoricosDto);
	}

	public void cadastrarHistorico(String token, Cidade cidade, Situacao situacao) {
		Usuario usuario = tokenService.getCredenciaisUsuario(token);

		CidadeHistorico cidadeHistorico = new CidadeHistorico(cidade, situacao, usuario);
		cidadeHistoricoService.cadastrar(cidadeHistorico);
	}
}