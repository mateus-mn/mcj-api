package com.mcj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.GrupoHistorico;

@Repository
public interface GrupoHistoricoRepository extends JpaRepository<GrupoHistorico, Long>
{
}