package com.mcj.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcj.api.dto.UsuarioHistoricoDto;
import com.mcj.api.model.Situacao;
import com.mcj.api.model.Usuario;
import com.mcj.api.model.UsuarioHistorico;
import com.mcj.api.service.TokenService;
import com.mcj.api.service.UsuarioHistoricoService;

@RestController
@RequestMapping("/usuarioHistorico")
public class UsuarioHistoricoController {
	@Autowired
	private UsuarioHistoricoService usuarioHistoricoService;
	@Autowired
	private TokenService tokenService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Usuário Histórico";
	}

	@GetMapping("/listar/{idUsuario}")
	@CrossOrigin
	public ResponseEntity<List<UsuarioHistoricoDto>> buscarHistorico(@PathVariable Long idUsuario) {
		List<UsuarioHistorico> usuarioHistorico = usuarioHistoricoService.listar(idUsuario);
		List<UsuarioHistoricoDto> usuarioHistoricosDto = usuarioHistoricoService.converterParaDto(usuarioHistorico);
		return ResponseEntity.ok(usuarioHistoricosDto);
	}

	public void cadastrarHistorico(String token, Usuario usuario, Long idSituacao) {
		Usuario usuarioLogado = tokenService.getCredenciaisUsuario(token);

		UsuarioHistorico usuarioHistorico = new UsuarioHistorico(usuario, new Situacao(idSituacao), usuarioLogado);
		usuarioHistoricoService.cadastrar(usuarioHistorico);
	}
}