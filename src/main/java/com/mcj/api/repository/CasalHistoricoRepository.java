package com.mcj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.CasalHistorico;

@Repository
public interface CasalHistoricoRepository extends JpaRepository<CasalHistorico, Long> {
	@Query(nativeQuery = true, value = "SELECT ch.* FROM casal_historico ch WHERE ch.casal_id = :idCasal ORDER BY ch.id DESC")
	List<CasalHistorico> buscarHistorico(@Param("idCasal") Long idCasal);
}