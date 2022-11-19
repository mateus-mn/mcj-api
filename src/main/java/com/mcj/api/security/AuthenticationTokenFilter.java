package com.mcj.api.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mcj.api.model.Usuario;
import com.mcj.api.repository.UsuarioRepository;

public class AuthenticationTokenFilter extends OncePerRequestFilter
{
	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AuthenticationTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository)
	{
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
	{
		String token = recuperarToken(request);
		Boolean tokenValido = tokenService.validarToken(token);

		if(Boolean.TRUE.equals(tokenValido))
		{
			autenticarUsuario(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private String recuperarToken(HttpServletRequest request)
	{
		String token = request.getHeader("Authorization");

		if(token == null)
		{
			return null;
		}

		if(token.isEmpty())
		{
			return null;
		}

		if(!token.startsWith("Bearer "))
		{
			return null;
		}

		return token.substring(7, token.length());
	}

	private void autenticarUsuario(String token)
	{
		Long idUsuario = tokenService.getIdUsuario(token);
		
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);

		if (optionalUsuario.isPresent())
		{
			Usuario usuario = optionalUsuario.get();
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}
}