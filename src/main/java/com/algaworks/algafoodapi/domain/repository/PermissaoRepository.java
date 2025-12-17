package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissaoRepository extends JpaRepository<Permissao, UUID>, JpaSpecificationExecutor<Permissao> {

    List<Permissao> findByNomeContaining(String nome);

    @Query("from Permissao p where p.id = ?1")
    Optional<Permissao> findById(UUID id);

    @Query("select coalesce(max(codInterno), 0) from Permissao ")
    Integer getLastCodInterno();
}
