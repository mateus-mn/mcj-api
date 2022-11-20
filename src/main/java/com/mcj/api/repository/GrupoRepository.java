package com.mcj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long>
{
}