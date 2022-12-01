package com.mcj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.Sexo;

@Repository
public interface SexoRepository extends JpaRepository<Sexo, Long> {
}