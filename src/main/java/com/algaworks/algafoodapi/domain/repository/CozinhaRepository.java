package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.Cozinha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, UUID> {

    List<Cozinha> findByNomeContaining(String nome);

    @Query("select coalesce(max(codInterno), 0) from Cozinha ")
    Integer getLastCodInterno();

    @Query("from Cozinha c where c.id = ?1")
    Optional<Cozinha> findById(UUID id);

    void deleteById(UUID id);
}
