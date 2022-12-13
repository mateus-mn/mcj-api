package com.mcj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}