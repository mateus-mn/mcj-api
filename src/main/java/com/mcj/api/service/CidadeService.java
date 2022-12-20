package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.CidadeDto;

import com.mcj.api.form.CidadeForm;

import com.mcj.api.model.Cidade;
import com.mcj.api.model.Estado;
import com.mcj.api.repository.CidadeRepository;

@Service
public class CidadeService {
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoService estadoService;

	public Cidade converter(CidadeForm cidadeForm) {
		Estado estado = estadoService.buscarPorId(cidadeForm.getEstado());

		return new Cidade(cidadeForm.getNome(), estado);
	}

	public List<CidadeDto> converterParaDto(List<Cidade> cidades) {
		List<CidadeDto> cidadesDto = new ArrayList<>();

		for (Cidade c : cidades) {
			CidadeDto cidadeDto = new CidadeDto(c);

			cidadesDto.add(cidadeDto);
		}

		return cidadesDto;
	}

	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	public Cidade buscarPorId(Long id) {
		Optional<Cidade> optionalCidade = cidadeRepository.findById(id);

		if (optionalCidade.isPresent()) {
			return optionalCidade.get();
		}

		return null;
	}

	public List<Cidade> buscarSomenteAtivos() {
		return cidadeRepository.buscarSomenteAtivos();
	}

	public List<Cidade> buscarSomenteInativos() {
		return cidadeRepository.buscarSomenteInativos();
	}

	public Cidade cadastrar(CidadeForm cidadeForm) {
		Cidade cidade = converter(cidadeForm);
		cidadeRepository.save(cidade);

		return cidade;
	}

	public Cidade alterar(Long id, CidadeForm form) {
		Cidade cidade = buscarPorId(id);

		Estado estado = estadoService.buscarPorId(form.getEstado());

		cidade.setNome(form.getNome());
		cidade.setEstado(estado);

		return cidade;
	}

	public Cidade desativar(Long id) {
		Cidade cidade = buscarPorId(id);
		cidade.setAtivo(false);
		return cidade;
	}

	public Cidade reativar(Long id) {
		Cidade cidade = buscarPorId(id);
		cidade.setAtivo(true);
		return cidade;
	}
}