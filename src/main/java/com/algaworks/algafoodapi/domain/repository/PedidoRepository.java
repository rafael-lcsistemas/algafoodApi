package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.domain.model.entity.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

//    Pedido findByData(Long codigo);

    @Query("from Pedido p " +
            "join fetch p.usuario " +
            "join fetch p.formaPagamento " +
            "join fetch p.restaurante r " +
            "join fetch r.cozinha ")
    List<Pedido> findAll();

    @Query("from Pedido p " +
            "join fetch p.usuario " +
            "join fetch p.formaPagamento " +
            "join fetch p.restaurante r join fetch r.cozinha " +
            "WHERE p.id = ?1")
    Pedido findById(long id);
}