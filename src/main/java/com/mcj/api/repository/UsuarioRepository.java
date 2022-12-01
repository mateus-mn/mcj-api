package com.mcj.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByLogin(String login);

	@Query(nativeQuery = true, value = "SELECT * FROM usuario WHERE ativo = true")
	List<Usuario> buscarSomenteAtivos();

	@Query(nativeQuery = true, value = "SELECT * FROM usuario WHERE ativo = false")
	List<Usuario> buscarSomenteInativos();
}