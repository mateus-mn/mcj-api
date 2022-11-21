package com.mcj.api.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mcj.api.model.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService
{
	@Value("${mcj.jwt.expiration}")
	private String expiration;

	@Value("${mcj.jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication)
	{
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date hoje = new Date();
		
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
		
		return
			Jwts
			.builder()
			.setIssuer("API do MCJ")
			.setId(usuario.getId().toString())
			.setSubject(usuario.getNome())
			.setIssuedAt(hoje)
			.setExpiration(dataExpiracao)
			.signWith(SignatureAlgorithm.HS256, this.secret)
			.compact();
	}

	public Boolean validarToken(String token)
	{
		try
		{
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	public Usuario getCredenciaisUsuario(String token)
	{
		if (token.contains("Bearer"))
		{
			token = token.substring(7, token.length());
		}

		Claims claims =  Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();

		Long id = Long.parseLong(claims.getId());
		String nome = claims.getSubject();

		return new Usuario(id, nome);
	}
}