package com.mcj.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcj.api.model.Bairro;
import com.mcj.api.model.BairroHistorico;
import com.mcj.api.model.Situacao;
import com.mcj.api.model.Usuario;
import com.mcj.api.dto.BairroHistoricoDto;
import com.mcj.api.service.BairroHistoricoService;
import com.mcj.api.service.TokenService;

@RestController
@RequestMapping("/bairroHistorico")
public class BairroHistoricoController {
	@Autowired
	private BairroHistoricoService bairroHistoricoService;
	@Autowired
	private TokenService tokenService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Bairro Histórico";
	}

	@GetMapping("/listar/{idBairro}")
	@CrossOrigin
	public ResponseEntity<List<BairroHistoricoDto>> buscarHistorico(@PathVariable Long idBairro) {
		List<BairroHistorico> bairroHistorico = bairroHistoricoService.listar(idBairro);
		List<BairroHistoricoDto> bairroHistoricosDto = bairroHistoricoService.converterParaDto(bairroHistorico);
		return ResponseEntity.ok(bairroHistoricosDto);
	}

	public void cadastrarHistorico(String token, Bairro bairro, Situacao situacao) {
		Usuario usuario = tokenService.getCredenciaisUsuario(token);

		BairroHistorico bairroHistorico = new BairroHistorico(bairro, situacao, usuario);
		bairroHistoricoService.cadastrar(bairroHistorico);
	}
}