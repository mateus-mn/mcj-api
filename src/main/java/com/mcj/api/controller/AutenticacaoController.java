package com.mcj.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcj.api.dto.TokenDto;
import com.mcj.api.form.LoginForm;
import com.mcj.api.model.Situacao;
import com.mcj.api.model.Usuario;
import com.mcj.api.service.TokenService;
import com.mcj.api.service.UsuarioHistoricoService;
import com.mcj.api.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController
{
	private String tipoAutenticacao = "Bearer";

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioHistoricoService usuarioHistoricoService;
	
	@GetMapping(value = {"", "/"})
	@CrossOrigin
	public String index()
	{
		return "Bem vindo à Entidade Autenticação";
	}
	
	@PostMapping("/login")
	@CrossOrigin
	public ResponseEntity<TokenDto> autenticar(@RequestBody LoginForm form)
	{
		UsernamePasswordAuthenticationToken dadosLogin = form.converter(form);

		try
		{
			Authentication authentication = authenticationManager.authenticate(dadosLogin);

			String token = tokenService.gerarToken(authentication);

			Usuario usuario = usuarioService.buscarPeloLogin(form.getLogin());

			// Obs.: o código 5 é referência para "logado"
			// Obs.2: aqui o histórico é uma auto referencia, pois é o próprio usuário que está logando no sistema
			usuarioHistoricoService.cadastrar(usuario, new Situacao(Long.valueOf(5)), usuario);
			
			return ResponseEntity.ok(new TokenDto(token, tipoAutenticacao, true, usuario.getId(), usuario.getNome()));
			
		}
		catch(AuthenticationException e)
		{
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/verificarToken")
	@CrossOrigin
	public ResponseEntity<TokenDto> verificarToken(@RequestBody String token)
	{
		Boolean isTOkenValido = tokenService.validarToken(token);

		try
		{
			if(Boolean.TRUE.equals(isTOkenValido))
			{
				Usuario usuario = tokenService.getCredenciaisUsuario(token);

				return ResponseEntity.ok(new TokenDto(token, tipoAutenticacao, true, usuario.getId(), usuario.getNome()));
			}
			else
			{
				return ResponseEntity.ok(new TokenDto(token, tipoAutenticacao, false, null, null));
			}
		}
		catch(AuthenticationException e)
		{
			return ResponseEntity.badRequest().build();
		}
	}
}