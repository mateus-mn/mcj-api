package com.mcj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM bairro WHERE ativo = true")
	List<Bairro> buscarSomenteAtivos();

	@Query(nativeQuery = true, value = "SELECT * FROM bairro WHERE ativo = false")
	List<Bairro> buscarSomenteInativos();
}