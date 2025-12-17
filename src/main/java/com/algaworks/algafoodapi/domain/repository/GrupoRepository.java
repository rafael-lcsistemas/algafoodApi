package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, UUID> {

    List<Grupo> findByNomeContaining(String nome);

    @Query("from Grupo p where p.id = ?1")
    Optional<Grupo> findById(UUID id);

    @Query("select coalesce(max(codInterno), 0) from Grupo ")
    Integer getLastCodInterno();
}
