package com.mcj.api.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.dto.CasalDto;
import com.mcj.api.form.CasalForm;
import com.mcj.api.model.Casal;
import com.mcj.api.model.CasalEndereco;
import com.mcj.api.model.Pessoa;
import com.mcj.api.repository.CasalRepository;

@Service
public class CasalService {
	@Autowired
	private CasalEnderecoService casalEnderecoService;
	@Autowired
	private PessoaService pessoaService;
	@Autowired
	private CasalRepository casalRepository;

	public Casal converter(CasalForm casalForm) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataCasamento = LocalDate.parse(casalForm.getDataCasamento(), formatter);

		Pessoa homem = pessoaService.buscarPorId(casalForm.getHomem());
		Pessoa mulher = pessoaService.buscarPorId(casalForm.getMulher());

		Casal casalPadrinho = buscarPorId(casalForm.getCasalPadrinho());

		return new Casal(homem, mulher, dataCasamento, casalPadrinho);
	}

	public List<CasalDto> converterParaDto(List<Casal> casais) {
		List<CasalDto> casaisDto = new ArrayList<>();

		for (Casal c : casais) {
			CasalEndereco endereco = casalEnderecoService.buscarMaisRecente(c.getId());
			CasalDto casalDto = new CasalDto(c, endereco);

			casaisDto.add(casalDto);
		}

		return casaisDto;
	}

	public List<Casal> listar() {
		return casalRepository.findAll();
	}

	public Casal buscarPorId(Long id) {
		Optional<Casal> optionalCasal = casalRepository.findById(id);

		if (optionalCasal.isPresent()) {
			return optionalCasal.get();
		}

		return null;
	}

	public List<Casal> buscarSomenteAtivos() {
		return casalRepository.buscarSomenteAtivos();
	}

	public List<Casal> buscarSomenteInativos() {
		return casalRepository.buscarSomenteInativos();
	}

	public Casal cadastrar(CasalForm casalForm) {
		Casal casal = converter(casalForm);
		casalRepository.save(casal);

		return casal;
	}

	public Casal alterar(Long id, CasalForm form) {
		Optional<Casal> optionalCasal = casalRepository.findById(id);
		if (optionalCasal.isPresent()) {
			Casal casal = optionalCasal.get();

			Pessoa homem = pessoaService.buscarPorId(form.getHomem());
			Pessoa mulher = pessoaService.buscarPorId(form.getMulher());

			Casal casalPadrinho = buscarPorId(form.getCasalPadrinho());

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dataCasamento = LocalDate.parse(form.getDataCasamento(), formatter);

			casal.setHomem(homem);
			casal.setMulher(mulher);
			casal.setDataCasamento(dataCasamento);
			casal.setCasalPadrinho(casalPadrinho);

			return casal;
		}

		return null;
	}

	public Casal desativar(Long id) {
		Optional<Casal> optionalCasal = casalRepository.findById(id);
		if (optionalCasal.isPresent()) {
			Casal casal = optionalCasal.get();

			casal.setAtivo(false);

			return casal;
		}

		return null;
	}

	public Casal reativar(Long id) {
		Optional<Casal> optionalCasal = casalRepository.findById(id);
		if (optionalCasal.isPresent()) {
			Casal casal = optionalCasal.get();

			casal.setAtivo(true);

			return casal;
		}

		return null;
	}
}