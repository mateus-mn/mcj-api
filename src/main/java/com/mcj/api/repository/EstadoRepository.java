package com.mcj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}