package com.mcj.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.model.Pessoa;
import com.mcj.api.model.PessoaCelular;
import com.mcj.api.repository.PessoaCelularRepository;

@Service
public class PessoaCelularService {
	@Autowired
	private PessoaCelularRepository pessoaCelularRepository;

	public PessoaCelular buscarMaisRecente(Long id) {
		return pessoaCelularRepository.buscarMaisRecente(id);
	}

	public PessoaCelular cadastrar(Pessoa pessoa, String celular) {
		if (celular != null && !celular.equals("")) {
			PessoaCelular pessoaCelular = new PessoaCelular(pessoa, celular, true);
			pessoaCelularRepository.save(pessoaCelular);
			return pessoaCelular;
		}

		return null;
	}

	public PessoaCelular alterar(Pessoa pessoa, String celular) {
		// verifica se o celular enviado é o mesmo ou diferente do atual, caso seja
		// diferente (ou ainda nao exista nenhum cadastrado), é necessário fazer um novo
		// insert no banco para se manter o histórico, caso já exista um registro e o
		// campo venha vazio, desativa o mais recente
		PessoaCelular pessoaCelular = buscarMaisRecente(pessoa.getId());

		// se nenhum celular veio do front e
		if (celular == null || celular.equals("")) {
			// se já existe algum celular na base, apenas desativa o atual
			if (pessoaCelular != null) {
				pessoaCelular.setAtivo(false);
			}

			return pessoaCelular;
		}

		// se veio um celular do front e
		else if (!celular.equals("")) {
			// não existe nenhum ativo ou cadastrado na base, adiciona o registro
			if (pessoaCelular == null) {
				PessoaCelular novoCelular = new PessoaCelular(pessoa, celular, true);
				pessoaCelularRepository.save(novoCelular);
			}
			// caso contrário, veio um celular do front e ele é diferente do cadastrado...
			else {
				// desativa o atual e cadastra o novo
				if (!pessoaCelular.getCelular().equals(celular)) {
					pessoaCelular.setAtivo(false);

					PessoaCelular novoCelular = new PessoaCelular(pessoa, celular, true);
					pessoaCelularRepository.save(novoCelular);
				}
			}
		}

		return pessoaCelular;
	}
}
