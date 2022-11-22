package com.mcj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>
{
	@Query
	(
		nativeQuery = true,
		value = "SELECT * FROM grupo WHERE ativo = true"
	)
	List<Grupo> buscarSomenteAtivos();

	@Query
	(
		nativeQuery = true,
		value = "SELECT * FROM grupo WHERE ativo = false"
	)
	List<Grupo> buscarSomenteInativos();
}