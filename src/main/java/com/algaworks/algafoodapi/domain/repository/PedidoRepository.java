package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID>, JpaSpecificationExecutor<Pedido> {

//    Pedido findByData(Long codigo);

    @Query("select coalesce(max(codInterno), 0) from Pedido")
    Long getLastCodInterno();

    @EntityGraph(attributePaths = {"usuario", "formaPagamento", "restaurante"})
    Page<Pedido> findAll(Specification spec, Pageable pageable);

    @Query("from Pedido p " +
            "join fetch p.usuario " +
            "join fetch p.formaPagamento " +
            "join fetch p.restaurante r join fetch r.cozinha " +
            "WHERE p.id = ?1")
    Optional<Pedido> findById(UUID id);
}