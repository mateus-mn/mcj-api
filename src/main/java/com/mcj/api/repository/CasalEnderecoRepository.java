package com.mcj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mcj.api.model.CasalEndereco;

@Repository
public interface CasalEnderecoRepository extends JpaRepository<CasalEndereco, Long> {
	@Query(nativeQuery = true, value = "SELECT ce.* FROM casal_endereco ce WHERE ce.ativo = true AND ce.id = (SELECT max(ce2.id) FROM casal_endereco ce2 WHERE ce2.casal_id = :idCasal)")
	CasalEndereco buscarMaisRecente(@Param("idCasal") Long idCasal);
}
