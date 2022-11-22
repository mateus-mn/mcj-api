package com.mcj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.GrupoHistorico;

@Repository
public interface GrupoHistoricoRepository extends JpaRepository<GrupoHistorico, Long>
{
	@Query
	(
		nativeQuery = true,
		value = "SELECT gh.* FROM grupo_historico gh WHERE gh.grupo_id = :idGrupo ORDER BY gh.id DESC"
	)
	List<GrupoHistorico> buscarHistorico(@Param("idGrupo") Long idGrupo);
}