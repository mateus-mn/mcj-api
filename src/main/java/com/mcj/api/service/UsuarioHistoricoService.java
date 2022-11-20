package com.mcj.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.model.Situacao;
import com.mcj.api.model.Usuario;
import com.mcj.api.model.UsuarioHistorico;
import com.mcj.api.repository.UsuarioHistoricoRepository;

@Service
public class UsuarioHistoricoService
{
	@Autowired
	private UsuarioHistoricoRepository usuarioHistoricoRepository;

	public void cadastrar (Usuario usuario, Situacao situacao, Usuario usuarioRegistro)
	{
		UsuarioHistorico usuarioHistorico = new UsuarioHistorico(usuario, situacao, usuarioRegistro);
		usuarioHistoricoRepository.save(usuarioHistorico);
	}
}
