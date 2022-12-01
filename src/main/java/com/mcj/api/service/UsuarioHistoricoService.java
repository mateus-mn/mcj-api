package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.UsuarioHistoricoDto;
import com.mcj.api.model.UsuarioHistorico;
import com.mcj.api.repository.UsuarioHistoricoRepository;

@Service
public class UsuarioHistoricoService {
	@Autowired
	private UsuarioHistoricoRepository usuarioHistoricoRepository;

	public List<UsuarioHistoricoDto> converterParaDto(List<UsuarioHistorico> usuarioHistoricos) {
		List<UsuarioHistoricoDto> usuarioHistoricosDto = new ArrayList<>();

		for (UsuarioHistorico uh : usuarioHistoricos) {
			UsuarioHistoricoDto usuarioHistoricoDto = new UsuarioHistoricoDto(uh);

			usuarioHistoricosDto.add(usuarioHistoricoDto);
		}

		return usuarioHistoricosDto;
	}

	public List<UsuarioHistorico> listar(Long idUsuario) {
		return usuarioHistoricoRepository.buscarHistorico(idUsuario);
	}

	public void cadastrar(UsuarioHistorico usuarioHistorico) {
		usuarioHistoricoRepository.save(usuarioHistorico);
	}
}
