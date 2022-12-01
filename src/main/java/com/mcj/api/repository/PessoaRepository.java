package com.mcj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM pessoa WHERE ativo = true")
	List<Pessoa> buscarSomenteAtivos();

	@Query(nativeQuery = true, value = "SELECT * FROM pessoa WHERE ativo = false")
	List<Pessoa> buscarSomenteInativos();
}