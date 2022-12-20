package com.mcj.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcj.api.model.Bairro;
import com.mcj.api.model.Casal;
import com.mcj.api.model.CasalEndereco;
import com.mcj.api.repository.CasalEnderecoRepository;

@Service
public class CasalEnderecoService {
	@Autowired
	private BairroService bairroService;
	@Autowired
	private CasalEnderecoRepository casalEnderecoRepository;

	public CasalEndereco buscarMaisRecente(Long id) {
		return casalEnderecoRepository.buscarMaisRecente(id);
	}

	public CasalEndereco cadastrar(Casal casal, String logradouro, Long numero, Long idBairro) {
		if (logradouro != null && !logradouro.equals("") && numero != null && idBairro != null) {
			Bairro bairro = bairroService.buscarPorId(idBairro);
			CasalEndereco casalEndereco = new CasalEndereco(casal, logradouro, numero, bairro, true);
			casalEnderecoRepository.save(casalEndereco);
			return casalEndereco;
		}

		return null;
	}

	public CasalEndereco alterar(Casal casal, String logradouro, Long numero, Long idBairro) {
		// verifica se o endereço enviado é o mesmo ou diferente do atual, caso seja
		// diferente (ou ainda nao exista nenhum cadastrado), é necessário fazer um novo
		// insert no banco para se manter o histórico, caso já exista um registro e o
		// campo venha vazio, desativa o mais recente
		CasalEndereco endereco = casalEnderecoRepository.buscarMaisRecente(casal.getId());

		// se nenhum endereco veio do front e
		if (logradouro.equals("") && numero == null && idBairro == null) {
			// se já existe algum endereço na base, apenas desativa o atual
			if (endereco != null) {
				endereco.setAtivo(false);
			}

			return endereco;
		}

		// se veio um endereço do front e
		if (!logradouro.equals("") && numero != null && idBairro != null) {
			// não existe nenhum ativo ou cadastrado na base, adiciona o registro
			if (endereco == null) {
				Bairro bairro = bairroService.buscarPorId(idBairro);
				CasalEndereco novoEndereco = new CasalEndereco(casal, logradouro, numero, bairro, true);
				casalEnderecoRepository.save(novoEndereco);

			}
			// caso contrário, veio um endereço do front e ele é diferente do cadastrado...
			else {
				// desativa o atual e cadastra o novo
				// se algum dos três estiver diferente, um novo registro é inserido
				Boolean flag = false;

				if (!endereco.getLogradouro().equals(logradouro)) {
					flag = true;
				}

				if (!endereco.getNumero().equals(numero)) {
					flag = true;
				}

				if (!endereco.getBairro().getId().equals(idBairro)) {
					flag = true;
				}

				if (Boolean.TRUE.equals(flag)) {
					endereco.setAtivo(false);

					Bairro bairro = bairroService.buscarPorId(idBairro);
					CasalEndereco novoEndereco = new CasalEndereco(casal, logradouro, numero, bairro, true);
					casalEnderecoRepository.save(novoEndereco);
				}
			}
		}

		return endereco;
	}
}
