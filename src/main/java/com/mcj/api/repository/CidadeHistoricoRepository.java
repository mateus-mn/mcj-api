package com.mcj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.CidadeHistorico;

@Repository
public interface CidadeHistoricoRepository extends JpaRepository<CidadeHistorico, Long> {
	@Query(nativeQuery = true, value = "SELECT ch.* FROM cidade_historico ch WHERE ch.cidade_id = :idCidade ORDER BY ch.id DESC")
	List<CidadeHistorico> buscarHistorico(@Param("idCidade") Long idCidade);
}