package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FabricanteRespository extends JpaRepository<Fabricante, UUID> {

    @Query("from Fabricante c where c.nome like %?1%")
    List<Fabricante> findFabricanteByNome(String nome);

    @Query("from Fabricante c where c.id = ?1 ")
    Optional<Fabricante> findById(UUID id);

    @Query("select coalesce(max(codInterno), 0) from Fabricante ")
    Integer getLastCodInterno();
}