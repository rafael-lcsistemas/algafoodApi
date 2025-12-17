package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaRespository extends JpaRepository<Categoria, UUID> {

    @Query("from Categoria c where c.nome like %?1%")
    List<Categoria> findCategoriaByNome(String nome);

    @Query("from Categoria c where c.id = ?1 ")
    Optional<Categoria> findById(UUID id);

    @Query("select coalesce(max(codInterno), 0) from Categoria ")
    Integer getLastCodInterno();
}