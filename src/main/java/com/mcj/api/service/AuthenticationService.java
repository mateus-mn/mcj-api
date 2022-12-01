package com.mcj.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mcj.api.model.Usuario;

@Service
public class AuthenticationService implements UserDetailsService {
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.buscarPorLogin(login);

		if (usuario != null) {
			return usuario;
		}

		throw new UsernameNotFoundException("Usuário ou senha incorretos!");
	}
}
