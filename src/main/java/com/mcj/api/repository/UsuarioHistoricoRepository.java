package com.mcj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.UsuarioHistorico;

@Repository
public interface UsuarioHistoricoRepository extends JpaRepository<UsuarioHistorico, Long>
{
}