package com.mcj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.PessoaEmail;

@Repository
public interface PessoaEmailRepository extends JpaRepository<PessoaEmail, Long> {
	@Query(nativeQuery = true, value = "SELECT pe.* FROM pessoa_email pe WHERE pe.ativo = true AND pe.id = (SELECT max(pe2.id) FROM pessoa_email pe2 WHERE pe2.pessoa_id = :idPessoa)")
	PessoaEmail buscarMaisRecente(@Param("idPessoa") Long idPessoa);
}
