package com.mcj.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.model.Pessoa;
import com.mcj.api.model.PessoaEmail;
import com.mcj.api.repository.PessoaEmailRepository;

@Service
public class PessoaEmailService {
	@Autowired
	private PessoaEmailRepository pessoaEmailRepository;

	public PessoaEmail buscarMaisRecente(Long id) {
		return pessoaEmailRepository.buscarMaisRecente(id);
	}

	public PessoaEmail cadastrar(Pessoa pessoa, String email) {
		if (email != null && !email.equals("")) {
			PessoaEmail pessoaEmail = new PessoaEmail(pessoa, email, true);
			pessoaEmailRepository.save(pessoaEmail);
			return pessoaEmail;
		}

		return null;
	}

	public PessoaEmail alterar(Pessoa pessoa, String email) {
		// verifica se o email enviado é o mesmo ou diferente do atual, caso seja
		// diferente (ou ainda nao exista nenhum cadastrado), é necessário fazer um novo
		// insert no banco para se manter o histórico, caso já exista um registro e o
		// campo venha vazio, desativa o mais recente
		PessoaEmail pessoaEmail = buscarMaisRecente(pessoa.getId());

		// se nenhum email veio do front e
		if (email == null || email.equals("")) {
			// se já existe algum email na base, apenas desativa o atual
			if (pessoaEmail != null) {
				pessoaEmail.setAtivo(false);
			}

			return pessoaEmail;
		}

		// se veio um email do front e
		if (!email.equals("")) {
			// não existe nenhum ativo ou cadastrado na base, adiciona o registro
			if (pessoaEmail == null) {
				PessoaEmail novoEmail = new PessoaEmail(pessoa, email, true);
				pessoaEmailRepository.save(novoEmail);
			}
			// caso contrário, veio um email do front e ele é diferente do cadastrado...
			else {
				// desativa o atual e cadastra o novo
				if (!pessoaEmail.getEmail().equals(email)) {
					pessoaEmail.setAtivo(false);

					PessoaEmail novoEmail = new PessoaEmail(pessoa, email, true);
					pessoaEmailRepository.save(novoEmail);
				}
			}
		}

		return pessoaEmail;
	}
}
