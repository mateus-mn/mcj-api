package com.mcj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.PessoaEmail;

@Repository
public interface PessoaEmailRepository extends JpaRepository<PessoaEmail, Long> {
	@Query(nativeQuery = true, value = "SELECT e.* FROM pessoa_email e WHERE e.ativo = true AND e.id = (SELECT max(e2.id) FROM pessoa_email e2 WHERE e2.pessoa_id = :idPessoa)")
	PessoaEmail buscarMaisRecente(@Param("idPessoa") Long idPessoa);
}
