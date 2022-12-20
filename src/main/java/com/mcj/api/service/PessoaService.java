package com.mcj.api.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.PessoaDto;
import com.mcj.api.form.PessoaForm;
import com.mcj.api.model.Pessoa;
import com.mcj.api.model.PessoaCelular;
import com.mcj.api.model.PessoaEmail;
import com.mcj.api.model.Sexo;
import com.mcj.api.repository.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
	private PessoaCelularService pessoaCelularService;
	@Autowired
	private PessoaEmailService pessoaEmailService;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private SexoService sexoService;

	public Pessoa converter(PessoaForm pessoaForm) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataNascimento = LocalDate.parse(pessoaForm.getDataNascimento(), formatter);

		Sexo sexo = sexoService.buscarPorId(pessoaForm.getSexo());

		return new Pessoa(pessoaForm.getNome(), sexo, dataNascimento);
	}

	public List<PessoaDto> converterParaDto(List<Pessoa> pessoas) {
		List<PessoaDto> pessoasDto = new ArrayList<>();

		for (Pessoa p : pessoas) {
			PessoaCelular celular = pessoaCelularService.buscarMaisRecente(p.getId());
			PessoaEmail email = pessoaEmailService.buscarMaisRecente(p.getId());
			PessoaDto pessoaDto = new PessoaDto(p, celular, email);

			pessoasDto.add(pessoaDto);
		}

		return pessoasDto;
	}

	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	public Pessoa buscarPorId(Long id) {
		Optional<Pessoa> optionalPessoa = pessoaRepository.findById(id);

		if (optionalPessoa.isPresent()) {
			return optionalPessoa.get();
		}

		return null;
	}

	public List<Pessoa> buscarSomenteAtivos() {
		return pessoaRepository.buscarSomenteAtivos();
	}

	public List<Pessoa> buscarSomenteInativos() {
		return pessoaRepository.buscarSomenteInativos();
	}

	public Pessoa cadastrar(PessoaForm pessoaForm) {
		Pessoa pessoa = converter(pessoaForm);
		pessoaRepository.save(pessoa);

		return pessoa;
	}

	public Pessoa alterar(Long id, PessoaForm form) {
		Pessoa pessoa = buscarPorId(id);

		Sexo sexo = sexoService.buscarPorId(form.getSexo());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataNascimento = LocalDate.parse(form.getDataNascimento(), formatter);

		pessoa.setNome(form.getNome());
		pessoa.setDataNascimento(dataNascimento);
		pessoa.setSexo(sexo);

		return pessoa;
	}

	public Pessoa desativar(Long id) {
		Pessoa pessoa = buscarPorId(id);
		pessoa.setAtivo(false);
		return pessoa;
	}

	public Pessoa reativar(Long id) {
		Pessoa pessoa = buscarPorId(id);
		pessoa.setAtivo(true);
		return pessoa;
	}
}