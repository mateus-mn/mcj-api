package com.mcj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.BairroHistorico;

@Repository
public interface BairroHistoricoRepository extends JpaRepository<BairroHistorico, Long> {
	@Query(nativeQuery = true, value = "SELECT bh.* FROM bairro_historico bh WHERE bh.bairro_id = :idBairro ORDER BY bh.id DESC")
	List<BairroHistorico> buscarHistorico(@Param("idBairro") Long idBairro);
}