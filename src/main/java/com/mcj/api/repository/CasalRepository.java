package com.mcj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.Casal;

@Repository
public interface CasalRepository extends JpaRepository<Casal, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM casal WHERE ativo = true")
	List<Casal> buscarSomenteAtivos();

	@Query(nativeQuery = true, value = "SELECT * FROM casal WHERE ativo = false")
	List<Casal> buscarSomenteInativos();
}