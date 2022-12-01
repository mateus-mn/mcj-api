package com.mcj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.PessoaHistorico;

@Repository
public interface PessoaHistoricoRepository extends JpaRepository<PessoaHistorico, Long> {
	@Query(nativeQuery = true, value = "SELECT ph.* FROM pessoa_historico ph WHERE ph.pessoa_id = :idPessoa ORDER BY ph.id DESC")
	List<PessoaHistorico> buscarHistorico(@Param("idPessoa") Long idPessoa);
}