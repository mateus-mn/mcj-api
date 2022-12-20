package com.mcj.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.BairroDto;
import com.mcj.api.form.BairroForm;
import com.mcj.api.model.Bairro;
import com.mcj.api.model.Cidade;
import com.mcj.api.repository.BairroRepository;

@Service
public class BairroService {
	@Autowired
	private BairroRepository bairroRepository;
	@Autowired
	private CidadeService cidadeService;

	public Bairro converter(BairroForm bairroForm) {
		Cidade cidade = cidadeService.buscarPorId(bairroForm.getCidade());

		return new Bairro(bairroForm.getNome(), cidade);
	}

	public List<BairroDto> converterParaDto(List<Bairro> bairros) {
		List<BairroDto> bairrosDto = new ArrayList<>();

		for (Bairro b : bairros) {
			BairroDto bairroDto = new BairroDto(b);

			bairrosDto.add(bairroDto);
		}

		return bairrosDto;
	}

	public List<Bairro> listar() {
		return bairroRepository.findAll();
	}

	public Bairro buscarPorId(Long id) {
		Optional<Bairro> optionalBairro = bairroRepository.findById(id);

		if (optionalBairro.isPresent()) {
			return optionalBairro.get();
		}

		return null;
	}

	public List<Bairro> buscarSomenteAtivos() {
		return bairroRepository.buscarSomenteAtivos();
	}

	public List<Bairro> buscarSomenteInativos() {
		return bairroRepository.buscarSomenteInativos();
	}

	public Bairro cadastrar(BairroForm bairroForm) {
		Bairro bairro = converter(bairroForm);
		bairroRepository.save(bairro);

		return bairro;
	}

	public Bairro alterar(Long id, BairroForm form) {
		Bairro bairro = buscarPorId(id);
		Cidade cidade = cidadeService.buscarPorId(form.getCidade());

		bairro.setNome(form.getNome());
		bairro.setCidade(cidade);

		return bairro;
	}

	public Bairro desativar(Long id) {
		Bairro bairro = buscarPorId(id);
		bairro.setAtivo(false);
		return bairro;
	}

	public Bairro reativar(Long id) {
		Bairro bairro = buscarPorId(id);
		bairro.setAtivo(true);
		return bairro;
	}
}