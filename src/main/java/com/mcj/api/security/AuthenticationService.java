package com.mcj.api.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mcj.api.model.Usuario;
import com.mcj.api.repository.UsuarioRepository;

@Service
public class AuthenticationService implements UserDetailsService
{
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException
	{
		Optional<Usuario> usuario = usuarioRepository.findByLogin(login);

		if(usuario.isPresent())
		{
			return usuario.get();
		}

		throw new UsernameNotFoundException("Usuário ou senha incorretos!");
	}
}
