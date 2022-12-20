package com.mcj.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcj.api.model.Casal;
import com.mcj.api.model.CasalHistorico;
import com.mcj.api.model.Situacao;
import com.mcj.api.model.Usuario;
import com.mcj.api.dto.CasalHistoricoDto;
import com.mcj.api.service.CasalHistoricoService;
import com.mcj.api.service.TokenService;

@RestController
@RequestMapping("/casalHistorico")
public class CasalHistoricoController {
	@Autowired
	private CasalHistoricoService casalHistoricoService;
	@Autowired
	private TokenService tokenService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Casal Histórico";
	}

	@GetMapping("/listar/{idCasal}")
	@CrossOrigin
	public ResponseEntity<List<CasalHistoricoDto>> buscarHistorico(@PathVariable Long idCasal) {
		List<CasalHistorico> casalHistorico = casalHistoricoService.listar(idCasal);
		List<CasalHistoricoDto> casalHistoricosDto = casalHistoricoService.converterParaDto(casalHistorico);
		return ResponseEntity.ok(casalHistoricosDto);
	}

	public void cadastrarHistorico(String token, Casal casal, Long idSituacao) {
		Usuario usuario = tokenService.getCredenciaisUsuario(token);

		CasalHistorico casalHistorico = new CasalHistorico(casal, new Situacao(idSituacao), usuario);
		casalHistoricoService.cadastrar(casalHistorico);
	}
}