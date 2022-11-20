package com.mcj.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.model.Usuario;
import com.mcj.api.repository.UsuarioRepository;

@Service
public class UsuarioService
{
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario buscarPeloLogin(String login)
	{
		Optional<Usuario> optionalUsuario = usuarioRepository.findByLogin(login);

		if (optionalUsuario.isPresent())
		{
			return optionalUsuario.get();
		}

		return null;
	}	
}
