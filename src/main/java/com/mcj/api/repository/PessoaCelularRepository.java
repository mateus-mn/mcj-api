package com.mcj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.PessoaCelular;

@Repository
public interface PessoaCelularRepository extends JpaRepository<PessoaCelular, Long> {
	@Query(nativeQuery = true, value = "SELECT c.* FROM pessoa_celular c WHERE c.ativo = true AND c.id = (SELECT max(c2.id) FROM pessoa_celular c2 WHERE c2.pessoa_id = :idPessoa)")
	PessoaCelular buscarMaisRecente(@Param("idPessoa") Long idPessoa);
}
