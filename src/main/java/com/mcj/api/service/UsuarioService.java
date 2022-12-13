package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.UsuarioDto;
import com.mcj.api.form.AlterarSenhaForm;
import com.mcj.api.form.UsuarioForm;
import com.mcj.api.model.Perfil;
import com.mcj.api.model.Usuario;
import com.mcj.api.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private PerfilService perfilService;
	@Autowired
	private UsuarioRepository usuarioRepository;

	public Usuario converter(UsuarioForm usuarioForm) {

		List<Perfil> perfis = new ArrayList<>();
		for (Long id : usuarioForm.getPerfis()) {
			Perfil perfil = perfilService.buscarPorId(id);
			perfis.add(perfil);
		}
		return new Usuario(usuarioForm.getNome(), usuarioForm.getLogin(), usuarioForm.getSenha(), perfis);
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

		// verifica se o usuário já está cadastrado na base
		Optional<Usuario> optionalUsuario = usuarioRepository.findByLogin(usuarioForm.getLogin());

		if (optionalUsuario.isPresent()) {
			return null;
		}

		String senhaBcrypt = new BCryptPasswordEncoder().encode(usuarioForm.getSenha());
		usuarioForm.setSenha(senhaBcrypt);

		Usuario usuario = converter(usuarioForm);
		usuarioRepository.save(usuario);

		return usuario;
	}

	public Usuario alterar(Long id, UsuarioForm form) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();

			// se o usuário atual for diferente do que está vindo do front, verifica se este
			// novo existe na base de dados
			if (!usuario.getLogin().equals(form.getLogin())) {
				Optional<Usuario> optionalUsuarioJaExiste = usuarioRepository.findByLogin(form.getLogin());

				if (optionalUsuarioJaExiste.isPresent()) {
					return null;
				}
			}

			List<Perfil> perfis = new ArrayList<>();
			for (Long idPerfil : form.getPerfis()) {
				Perfil perfil = perfilService.buscarPorId(idPerfil);
				perfis.add(perfil);
			}

			usuario.setNome(form.getNome());
			usuario.setLogin(form.getLogin());
			usuario.setPerfis(perfis);

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

	public Usuario alterarSenha(Long id, AlterarSenhaForm form) {
		Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);

		if (optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();

			String novaSenhaBcrypt = new BCryptPasswordEncoder().encode(form.getNovaSenha());
			usuario.setSenha(novaSenhaBcrypt);
			return usuario;
		}

		return null;
	}
}
