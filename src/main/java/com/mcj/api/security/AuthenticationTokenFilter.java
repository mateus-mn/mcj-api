package com.mcj.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mcj.api.model.Usuario;
import com.mcj.api.service.TokenService;

public class AuthenticationTokenFilter extends OncePerRequestFilter
{
	private TokenService tokenService;

	public AuthenticationTokenFilter(TokenService tokenService)
	{
		this.tokenService = tokenService;
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
		Usuario usuario = tokenService.getCredenciaisUsuario(token);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}