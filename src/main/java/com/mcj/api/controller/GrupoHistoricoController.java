package com.mcj.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcj.api.model.Grupo;
import com.mcj.api.model.GrupoHistorico;
import com.mcj.api.model.Situacao;
import com.mcj.api.model.Usuario;
import com.mcj.api.service.GrupoHistoricoService;
import com.mcj.api.service.TokenService;

@RestController
@RequestMapping("/grupoHistorico")
public class GrupoHistoricoController
{
	@Autowired
	private GrupoHistoricoService grupoHistoricoService;
	@Autowired
	private TokenService tokenService;
	
	@GetMapping(value = {"", "/"})
	@CrossOrigin
	public String index()
	{
		return "Bem vindo à Entidade Grupo Histórico";
	}

	public void cadastrarHistorico (String token, Grupo grupo, Long idSituacao)
	{
		Usuario usuario = tokenService.getCredenciaisUsuario(token);

		GrupoHistorico grupoHistorico = new GrupoHistorico(grupo, new Situacao(idSituacao), usuario);
		grupoHistoricoService.cadastrar(grupoHistorico);
	}
}