package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.UsuarioDto;
import com.mcj.api.form.UsuarioForm;
import com.mcj.api.model.Usuario;
import com.mcj.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario converter(UsuarioForm usuarioForm) {
		return new Usuario(usuarioForm.getNome(), usuarioForm.getLogin(), usuarioForm.getSenha());
	}

	public List<UsuarioDto> converterParaDto(List<Usuario> usuarios) {
		List<UsuarioDto> usuariosDto = new ArrayList<>();

		for (Usuario u : usuarios) {
			UsuarioDto usuarioDto = new UsuarioDto(u);

			usuariosDto.add(usuarioDto);
		}

		return usuariosDto;
	}

	public List<Usuario> listar() {
		return usuarioRepository.findAll();
	}

	public Usuario buscarPorId(Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

		if (optionalUsuario.isPresent()) {
			return optionalUsuario.get();
		}

		return null;
	}

	public Usuario buscarPorLogin(String login) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findByLogin(login);

		if (optionalUsuario.isPresent()) {
			return optionalUsuario.get();
		}

		return null;
	}

	public List<Usuario> buscarSomenteAtivos() {
		return usuarioRepository.buscarSomenteAtivos();
	}

	public List<Usuario> buscarSomenteInativos() {
		return usuarioRepository.buscarSomenteInativos();
	}

	public Usuario cadastrar(UsuarioForm usuarioForm) {
		Usuario usuario = converter(usuarioForm);
		usuarioRepository.save(usuario);

		return usuario;
	}

	public Usuario alterar(Long id, UsuarioForm form) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();

			usuario.setNome(form.getNome());
			usuario.setLogin(form.getLogin());
			usuario.setSenha(form.getSenha());

			return usuario;
		}

		return null;
	}

	public Usuario desativar(Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();

			usuario.setAtivo(false);

			return usuario;
		}

		return null;
	}

	public Usuario reativar(Long id) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();

			usuario.setAtivo(true);

			return usuario;
		}

		return null;
	}
}
