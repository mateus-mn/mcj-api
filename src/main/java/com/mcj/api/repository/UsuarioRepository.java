package com.mcj.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
	Optional<Usuario> findByLogin(String login);
}
