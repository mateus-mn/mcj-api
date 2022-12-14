package com.mcj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM cidade WHERE ativo = true")
	List<Cidade> buscarSomenteAtivos();

	@Query(nativeQuery = true, value = "SELECT * FROM cidade WHERE ativo = false")
	List<Cidade> buscarSomenteInativos();
}