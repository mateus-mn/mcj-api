package com.mcj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.PessoaCelular;

@Repository
public interface PessoaCelularRepository extends JpaRepository<PessoaCelular, Long> {
	@Query(nativeQuery = true, value = "SELECT pc.* FROM pessoa_celular pc WHERE pc.ativo = true AND pc.id = (SELECT max(pc2.id) FROM pessoa_celular pc2 WHERE pc2.pessoa_id = :idPessoa)")
	PessoaCelular buscarMaisRecente(@Param("idPessoa") Long idPessoa);
}
