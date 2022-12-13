package com.mcj.api.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mcj.api.dto.TotalDto;
import com.mcj.api.dto.UsuarioDto;
import com.mcj.api.form.AlterarSenhaForm;
import com.mcj.api.form.UsuarioForm;
import com.mcj.api.model.Usuario;
import com.mcj.api.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioHistoricoController usuarioHistoricoController;
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(value = { "", "/" })
	@CrossOrigin
	public String index() {
		return "Bem vindo à Entidade Usuário";
	}

	@GetMapping("/listar")
	@CrossOrigin
	public ResponseEntity<List<UsuarioDto>> listar() {
		try {
			List<Usuario> usuarios = usuarioService.listar();
			List<UsuarioDto> usuariosDto = usuarioService.converterParaDto(usuarios);
			return ResponseEntity.ok(usuariosDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/listar/{id}")
	@CrossOrigin
	public ResponseEntity<List<UsuarioDto>> listar(@PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		try {
			Usuario usuario = usuarioService.buscarPorId(id);

			if (usuario != null) {
				// Obs.: o código 3 é referência para "acessado"
				usuarioHistoricoController.cadastrarHistorico(token, usuario, Long.valueOf(3));
			}

			List<Usuario> usuarios = new ArrayList<>();
			usuarios.add(usuario);

			List<UsuarioDto> usuariosDto = usuarioService.converterParaDto(usuarios);

			return ResponseEntity.ok(usuariosDto);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/listarTotais")
	@CrossOrigin
	public ResponseEntity<TotalDto> listarTotais() {
		List<Usuario> usuarios = usuarioService.listar();

		int totalAtivos = 0;
		int totalInativos = 0;
		for (Usuario u : usuarios) {
			if (Boolean.TRUE.equals(u.getAtivo())) {
				totalAtivos++;
			} else {
				totalInativos++;
			}
		}

		TotalDto totais = new TotalDto(usuarios.size(), totalAtivos, totalInativos);
		return ResponseEntity.ok(totais);
	}

	@GetMapping("/listarAtivos")
	@CrossOrigin
	public ResponseEntity<List<UsuarioDto>> listarAtivos() {
		List<Usuario> usuarios = usuarioService.buscarSomenteAtivos();
		List<UsuarioDto> usuariosDto = usuarioService.converterParaDto(usuarios);
		return ResponseEntity.ok(usuariosDto);
	}

	@GetMapping("/listarInativos")
	@CrossOrigin
	public ResponseEntity<List<UsuarioDto>> listarInativos() {
		List<Usuario> usuarios = usuarioService.buscarSomenteInativos();
		List<UsuarioDto> usuariosDto = usuarioService.converterParaDto(usuarios);
		return ResponseEntity.ok(usuariosDto);
	}

	@PostMapping("/cadastrar")
	@Transactional
	@CrossOrigin
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody UsuarioForm form,
			UriComponentsBuilder uriComponentsBuilder,
			@RequestHeader("Authorization") String token) {
		try {
			Usuario usuario = usuarioService.cadastrar(form);

			if (usuario == null) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}

			// Obs.: o código 1 é referência para "cadastrado"
			usuarioHistoricoController.cadastrarHistorico(token, usuario, Long.valueOf(1));

			URI uri = uriComponentsBuilder.path("/pessoa/listar/{id}").buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/alterar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<UsuarioDto> alterar(@RequestBody UsuarioForm form, @PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		try {
			Usuario usuario = usuarioService.alterar(id, form);

			if (usuario == null) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}

			// Obs.: o código 2 é referência para "alterado"
			usuarioHistoricoController.cadastrarHistorico(token, usuario, Long.valueOf(2));

			return ResponseEntity.ok(new UsuarioDto(usuario));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/desativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<UsuarioDto> desativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Usuario usuario = usuarioService.desativar(id);

			// Obs.: o código 4 é referência para "desativado"
			usuarioHistoricoController.cadastrarHistorico(token, usuario, Long.valueOf(4));

			return ResponseEntity.ok(new UsuarioDto(usuario));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/reativar/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<UsuarioDto> reativar(@PathVariable Long id, @RequestHeader("Authorization") String token) {
		try {
			Usuario usuario = usuarioService.reativar(id);

			// Obs.: o código 5 é referência para "reativado"
			usuarioHistoricoController.cadastrarHistorico(token, usuario, Long.valueOf(5));

			return ResponseEntity.ok(new UsuarioDto(usuario));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/alterarSenha/{id}")
	@Transactional
	@CrossOrigin
	public ResponseEntity<UsuarioDto> alterarSenha(@RequestBody AlterarSenhaForm form, @PathVariable Long id,
			@RequestHeader("Authorization") String token) {
		try {
			Usuario usuario = usuarioService.alterarSenha(id, form);

			// Obs.: o código 9 é referência para "senha alterada"
			usuarioHistoricoController.cadastrarHistorico(token, usuario, Long.valueOf(9));

			return ResponseEntity.ok(new UsuarioDto(usuario));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}