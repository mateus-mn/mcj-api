package com.mcj.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.UsuarioHistorico;

@Repository
public interface UsuarioHistoricoRepository extends JpaRepository<UsuarioHistorico, Long> {
	@Query(nativeQuery = true, value = "SELECT uh.* FROM usuario_historico uh WHERE uh.usuario_id = :idUsuario ORDER BY uh.id DESC")
	List<UsuarioHistorico> buscarHistorico(@Param("idUsuario") Long idUsuarLong);
}