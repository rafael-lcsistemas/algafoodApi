package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, UUID> {

    List<FormaPagamento> findByNomeContaining(String nome);

    @Query("from FormaPagamento p where p.id = ?1")
    Optional<FormaPagamento> findById(UUID id);

    void deleteById(UUID id);

    @Query("select coalesce(max(codInterno), 0) from FormaPagamento ")
    Integer getLastCodInterno();
}
